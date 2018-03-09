package cc.easyandroid.easyclean.httpextend.call;

import java.io.IOException;

import cc.easyandroid.easyclean.httpextend.easycore.CacheMode;
import cc.easyandroid.easyclean.httpextend.easycore.EasyExecutor;
import cc.easyandroid.easyclean.httpextend.easycore.EasyHttpStateCallback;
import cc.easyandroid.easyclean.httpextend.easycore.EasyResponse;
import okhttp3.Request;

/**
 * RetrofitCall 转换为EasyCall
 */
public class RetrofitCallToEasyCall<T> implements EasyCall<T> {
    private final retrofit2.Call rawCall;
    private boolean executed; // Guarded by this.
    private volatile boolean canceled;

    public RetrofitCallToEasyCall(retrofit2.Call call) {
        this.rawCall = call;
    }

    @Override
    public void enqueue(final EasyHttpStateCallback<T> callback, String tag) {
        synchronized (this) {
            if (executed)
                throw new IllegalStateException("Already enqueue");
            executed = true;
        }
        exeRequest(callback, tag);
    }

    /**
     * @param callback
     */
    private void exeRequest(final EasyHttpStateCallback<T> callback, String tag) {
        if (canceled) {
            rawCall.cancel();
            return;
        }
        if (rawCall instanceof CacheMode.ICacheModeInject) {//
            CacheMode.ICacheModeInject cacheModeInject = (CacheMode.ICacheModeInject) rawCall;
            cacheModeInject.setCacheMode(tag);//将手动设置的缓存模式传人
        }
        rawCall.enqueue(new retrofit2.Callback<T>() {
            private void callFailure(final Throwable e) {
                e.printStackTrace();
                EasyExecutor.getMainExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e);
                    }
                });
            }

            private void callSuccess(final EasyResponse<T> easyResponse) {
                EasyExecutor.getMainExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(easyResponse);
                    }
                });
            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable e) {//thread run
                e.printStackTrace();
                if (canceled) {
                    return;
                }
                callFailure(e);
            }

            @Override
            public void onResponse(retrofit2.Call<T> rawCall, retrofit2.Response<T> rawResponse) {
                if (canceled) {
                    return;
                }
                try {
                    T rawBody = rawResponse.body();
                    EasyResponse<T> easyResponse = EasyResponse.success(rawBody);
                    callSuccess(easyResponse);
                } catch (Throwable e) {
                    e.printStackTrace();
                    callFailure(e);
                    return;
                }
            }
        });
    }

    @Deprecated
    public EasyResponse<T> execute() throws IOException {
        //not use
        return null;
    }

    @Override
    public void cancel() {
        canceled = true;
        retrofit2.Call rawCall = this.rawCall;
        if (rawCall != null) {
            rawCall.cancel();
        }
    }

    @Override
    public boolean isCancel() {
        return canceled;
    }

    @Override
    public EasyCall<T> clone() {
        return new RetrofitCallToEasyCall<>(rawCall.clone());
    }

    @Override
    public boolean isExecuted() {
        return executed;
    }

    @Override
    public Request request() {
        return rawCall.request();
    }
}