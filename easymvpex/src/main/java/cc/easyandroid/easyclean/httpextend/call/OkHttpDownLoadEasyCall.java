package cc.easyandroid.easyclean.httpextend.call;

import java.io.File;
import java.io.IOException;

import cc.easyandroid.easyclean.httpextend.easycore.EAResult;
import cc.easyandroid.easyclean.httpextend.easycore.EasyExecutor;
import cc.easyandroid.easyclean.httpextend.easycore.EasyHttpStateCallback;
import cc.easyandroid.easyclean.httpextend.easycore.EasyResponse;
import cc.easyandroid.easyclean.httpextend.easycore.Utils;
import cc.easyandroid.easylog.EasyLog;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpDownLoadEasyCall implements EasyCall<OkHttpDownLoadEasyCall.DownLoadResult> {
    public static final String TAG = "OkHttpDownLoadEasyCall";
    protected final OkHttpClient client;
    protected final File file;
    private final Request request;
    private volatile okhttp3.Call rawCall;
    private boolean executed; // Guarded by this.
    private volatile boolean canceled;

    public OkHttpDownLoadEasyCall(OkHttpClient client, Request request, File file) {
        this.client = client;
        this.request = request;
        this.file = file;
    }

    @Override
    public void enqueue(final EasyHttpStateCallback<DownLoadResult> callback, String tag) {
        synchronized (this) {
            if (executed)
                throw new IllegalStateException("Already executed");
            executed = true;
        }
        exeRequest(callback, request);
    }

    private void exeRequest(final EasyHttpStateCallback<DownLoadResult> callback, Request request) {
        okhttp3.Call rawCall;
        rawCall = client.newCall(request);
        if (canceled) {
            rawCall.cancel();
            return;
        }
        this.rawCall = rawCall;

        rawCall.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, final IOException e) {//thread run
                e.printStackTrace();
                if (canceled) {
                    return;
                }
                EasyExecutor.getMainExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call rawCall, okhttp3.Response rawResponse) {
                if (canceled) {
                    return;
                }
                EasyResponse<DownLoadResult> easyResponse;
                if (rawResponse.isSuccessful()) {
                    boolean writtenToDisk = Utils.writeResponseBodyToDisk(rawResponse.body(), file, OkHttpDownLoadEasyCall.this);
                    easyResponse = EasyResponse.success(DownLoadResult.createDownLoadResult(writtenToDisk));
                } else {
                    EasyLog.e(TAG, "server contact failed not isSuccessful");
                    easyResponse = EasyResponse.error(-1, " not isSuccessful");
                }
                if (canceled) {
                    return;
                }
                final EasyResponse<DownLoadResult> easyResponse_final = easyResponse;
                EasyExecutor.getMainExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(easyResponse_final);
                    }
                });
            }
        });
    }

    @Override
    public void cancel() {
        canceled = true;
        okhttp3.Call rawCall = this.rawCall;
        if (rawCall != null) {
            rawCall.cancel();
        }
    }

    @Override
    public boolean isCancel() {
        return canceled;
    }

    @Override
    public EasyCall<DownLoadResult> clone() {
        return new OkHttpDownLoadEasyCall(client, request, file);
    }

    @Override
    public boolean isExecuted() {
        return executed;
    }
    @Override
    public Request request() {
        return request;
    }
    public static class DownLoadResult implements EAResult {
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

        public static DownLoadResult createDownLoadResult(boolean success) {
            DownLoadResult downLoadResult = new DownLoadResult();
            downLoadResult.setSuccess(success);
            return downLoadResult;
        }
    }
}
