package cc.easyandroid.easyclean.httpextend.easycore;

import android.text.TextUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cc.easyandroid.easyclean.httpextend.easycache.EasyHttpCache;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 如果使用retrofit2，这个类集成cache
 * CallAdapter 集成cache
 */
public class EasyExecutorCallAdapterFactory extends CallAdapter.Factory {
    EasyHttpCache cache;

    public EasyExecutorCallAdapterFactory(EasyHttpCache cache) {
        this.cache = cache;
    }

    @Override
    public CallAdapter<Call<?>> get(Type returnType, final Annotation[] annotations, final Retrofit retrofit) {
        if (getRawType(returnType) != Call.class) {
            return null;
        }
        final Type responseType = getCallResponseType(returnType);
        return new CallAdapter<Call<?>>() {
            @Override
            public Type responseType() {
                return responseType;
            }

            @Override
            public <R> Call<R> adapt(Call<R> call) {
                return new ExecutorCallbackCall<>(responseType, annotations, call, retrofit, cache);
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static <T> Converter<T, RequestBody> getRequestConverter(Retrofit retrofit, Type dataType, Annotation[] annotations) {
        return retrofit.requestBodyConverter(dataType, new Annotation[0], annotations);
    }

    @SuppressWarnings("unchecked")
    public static <T> Converter<ResponseBody, T> getResponseConverter(Retrofit retrofit, Type dataType, Annotation[] annotations) {
        return retrofit.responseBodyConverter(dataType, annotations);
    }

    static final class ExecutorCallbackCall<T> implements Call<T>, CacheMode.ICacheModeInject {
        final Call<T> delegate;
        final Type responseType;
        final Retrofit retrofit;
        final Annotation[] annotations;
        final EasyHttpCache cache;
        ;

        ExecutorCallbackCall(Type responseType, Annotation[] annotations, Call<T> delegate, Retrofit retrofit, EasyHttpCache cache) {
            this.delegate = delegate;
            this.retrofit = retrofit;
            this.responseType = responseType;
            this.annotations = annotations;
            this.cache = cache;
        }

        String cacheMode = null;

        public void setCacheMode(String cacheMode) {
            this.cacheMode = cacheMode;
        }

        private String getCacheMode(Request request) {
            return TextUtils.isEmpty(cacheMode) ? request.header("Cache-Mode") : cacheMode;
        }

        @Override
        public void enqueue(final Callback<T> callback) {
            if (callback == null) throw new NullPointerException("callback == null");
            final Request request = delegate.request();
            String cacheMode = getCacheMode(request);
            // ----------------------------------------------------------------------cgp
            if (!TextUtils.isEmpty(cacheMode)) {
                switch (cacheMode) {//缓存策越
                    case CacheMode.LOAD_NETWORK_ELSE_CACHE:// 先网络然后再缓存
                        exeRequest(callback, request, true);
                        return;
                    case CacheMode.LOAD_CACHE_ELSE_NETWORK:// 先缓存再网络
                        // ---------------------从缓存中取
                        EasyExecutor.getThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                final Response<T> easyResponse = execCacheRequest(request);
                                if (easyResponse == null) {
                                    exeRequest(callback, request, false);
                                } else {
                                    EasyExecutor.getMainExecutor().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            callback.onResponse(delegate, easyResponse);
                                        }
                                    });
                                }
                            }
                        });
                        return;
                    // ---------------------从缓存中取
                    // 如果缓存没有就跳出，执行网络请求
                    case CacheMode.LOAD_DEFAULT:
                    case CacheMode.LOAD_NETWORK_ONLY:
                    default:
                        break;// 直接跳出
                }
            }
            // ----------------------------------------------------------------------cgp
            exeRequest(callback, request, false);
        }

        /**
         * 根据request，从缓存中取出数据
         *
         * @param request request
         * @return Response
         */
        private Response<T> execCacheRequest(Request request) {
            ResponseBody responseBody = cache.get(request);
            if (responseBody != null) {
                Converter<ResponseBody, T> converter = getResponseConverter(retrofit, responseType, annotations);
                try {
                    T t = converter.convert(responseBody);
                    return Response.success(t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        /**
         * @param callback               回调接口
         * @param request                request
         * @param ifFailedToLoadTheCache 这个请求是从哪里来的，如果是从网络，请求网络失败后是否要从缓存中拿数据
         */
        public void exeRequest(final Callback<T> callback, final Request request, final boolean ifFailedToLoadTheCache) {
            delegate.enqueue(new Callback<T>() {
                @Override
                public void onResponse(final Call<T> call, final Response<T> response) {//子线程
                    if (delegate.isCanceled()) {
                        // Emulate OkHttp's behavior of throwing/delivering an IOException on cancellation.
                        callback.onFailure(ExecutorCallbackCall.this, new EasyException("Canceled"));
                    } else {
                        if (response != null && response.body() != null) {
                            T t = response.body();
                            if (t instanceof EAResult) {
                                EAResult eaResult = (EAResult) t;
                                if (eaResult.isSuccess()) {//成功
                                    //TODO： 先缓存，然后给用户数据，防止用户编辑数据后缓存的数据不正确
                                    cacheResponse(response, request);
                                    callback.onResponse(ExecutorCallbackCall.this, response);
                                } else  {
                                    onFailure(call, new EasyException(eaResult.getEADesc(),eaResult.getEACode()));
                                }
                            } else {//这里也是成功
                                callback.onResponse(ExecutorCallbackCall.this, response);
                            }
                        } else {
                            onFailure(call, new EasyException());
                        }
                    }
                }

                @Override
                public void onFailure(final Call<T> call, final Throwable t) {
                    //网络请求失败，回调
                    if (ifFailedToLoadTheCache) {//失败后拿缓存的数据
                        final Response<T> wrapper = execCacheRequest(request);
                        if (wrapper != null) {
                            callback.onResponse(call, wrapper);
                        } else {
                            callback.onFailure(ExecutorCallbackCall.this, t);
                        }
                    } else {
                        callback.onFailure(ExecutorCallbackCall.this, t);
                    }
                }
            });
        }

        private void cacheResponse(Response<T> response, Request request) {
            //这里要将数据缓存
            try {
                if (response != null && response.body() != null) {
                    Converter<T, RequestBody> converter = getRequestConverter(retrofit, responseType, annotations);
                    Buffer buffer = new Buffer();
                    RequestBody requestBody = converter.convert(response.body());
                    requestBody.writeTo(buffer);//对象转byte[]
                    cache.put(request, response.body(), buffer.readByteArray());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean isExecuted() {
            return delegate.isExecuted();
        }

        @Override
        public Response<T> execute() throws IOException {
            return delegate.execute();
        }

        @Override
        public void cancel() {
            delegate.cancel();
        }

        @Override
        public boolean isCanceled() {
            return delegate.isCanceled();
        }

        @SuppressWarnings("CloneDoesntCallSuperClone") // Performing deep clone.
        @Override
        public Call<T> clone() {
            return new ExecutorCallbackCall<>(responseType, annotations, delegate.clone(), retrofit,cache);
        }

        @Override
        public Request request() {
            return delegate.request();
        }
    }

    static Type getCallResponseType(Type returnType) {
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalArgumentException(
                    "Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
        }
        return getParameterUpperBound(0, (ParameterizedType) returnType);
    }
}