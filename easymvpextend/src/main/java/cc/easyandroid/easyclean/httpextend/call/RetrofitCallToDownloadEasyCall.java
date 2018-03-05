package cc.easyandroid.easyclean.httpextend.call;

import java.io.File;
import java.io.IOException;

import cc.easyandroid.easyclean.httpextend.easycore.CacheMode;
import cc.easyandroid.easyclean.httpextend.easycore.EAResult;
import cc.easyandroid.easyclean.httpextend.easycore.EasyExecutor;
import cc.easyandroid.easyclean.httpextend.easycore.EasyHttpStateCallback;
import cc.easyandroid.easyclean.httpextend.easycore.EasyResponse;
import cc.easyandroid.easyclean.httpextend.easycore.Utils;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * RetrofitCall 转换为EasyCall
 */
public class RetrofitCallToDownloadEasyCall implements EasyCall<RetrofitCallToDownloadEasyCall.EasyDownLoadResult> {
    private final retrofit2.Call rawCall;
    private boolean executed; // Guarded by this.
    private File file; // Guarded by this.
    private volatile boolean canceled;

    public RetrofitCallToDownloadEasyCall(retrofit2.Call call, File file) {
        this.rawCall = call;
        this.file = file;
    }

    @Override
    public void enqueue(final EasyHttpStateCallback<EasyDownLoadResult> callback, String tag) {
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
    private void exeRequest(final EasyHttpStateCallback<EasyDownLoadResult> callback, String tag) {
        if (canceled) {
            rawCall.cancel();
            return;
        }
        if (rawCall instanceof CacheMode.ICacheModeInject) {//
            CacheMode.ICacheModeInject cacheModeInject = (CacheMode.ICacheModeInject) rawCall;
            cacheModeInject.setCacheMode(tag);//将手动设置的缓存模式传人
        }
        rawCall.enqueue(new retrofit2.Callback<ResponseBody>() {
            private void callFailure(final Throwable e) {
                e.printStackTrace();
                EasyExecutor.getMainExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e);
                    }
                });
            }

            private void callSuccess(final EasyResponse<EasyDownLoadResult> easyResponse) {
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
            public void onResponse(retrofit2.Call<ResponseBody> rawCall, retrofit2.Response<ResponseBody> rawResponse) {
                if (canceled) {
                    return;
                }
                try {
                    ResponseBody rawBody = rawResponse.body();
//                    ResponseBody responseBody = (ResponseBody) rawBody;
                    EasyResponse<EasyDownLoadResult> easyResponse;
                    if (rawResponse.isSuccessful()) {
                        boolean writtenToDisk = Utils.writeResponseBodyToDisk(rawBody, file, RetrofitCallToDownloadEasyCall.this);
                        easyResponse = EasyResponse.success(EasyDownLoadResult.createDownLoadResult(writtenToDisk));
                    } else {
                        easyResponse = EasyResponse.error(-1, " not isSuccessful");
                    }
                    if (canceled) {
                        return;
                    }
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
    public EasyResponse<ResponseBody> execute() throws IOException {
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
    public EasyCall<EasyDownLoadResult> clone() {
        return new RetrofitCallToDownloadEasyCall(rawCall.clone(), file);
    }

    @Override
    public boolean isExecuted() {
        return executed;
    }

    @Override
    public Request request() {
        return rawCall.request();
    }

    public static class EasyDownLoadResult implements EAResult {
        private boolean success;

        public void setSuccess(boolean success) {
            this.success = success;
        }

        @Override
        public boolean isSuccess() {
            return success;
        }

        @Override
        public String getEADesc() {
            return "";
        }

        @Override
        public String getEACode() {
            return "";
        }

        public static EasyDownLoadResult createDownLoadResult(boolean success) {
            EasyDownLoadResult downLoadResult = new EasyDownLoadResult();
            downLoadResult.setSuccess(success);
            return downLoadResult;
        }
    }
}