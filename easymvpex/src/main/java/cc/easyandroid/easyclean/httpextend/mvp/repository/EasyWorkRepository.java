package cc.easyandroid.easyclean.httpextend.mvp.repository;

import cc.easyandroid.easyclean.httpextend.call.EasyCall;
import cc.easyandroid.easyclean.httpextend.easycore.EasyHttpStateCallback;
import cc.easyandroid.easyclean.httpextend.easycore.EasyResponse;
import cc.easyandroid.easylog.EasyLog;

/**
 * http的数据仓库，其实数据最终来自Model也就是这里的easycall
 */
public class EasyWorkRepository implements EasyWorkDataSource {

    public <T> void executeRequest(EasyCall<T> easyCall, final HttpRequestCallback<T> callback) {
        easyCall.enqueue(new EasyHttpStateCallback<T>() {
            @Override
            public void onResponse(EasyResponse<T> easyResponse) {
                callback.onResponse(easyResponse);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        }, null);
    }

    public <T> void executeRequest(EasyCall<T> easyCall, final HttpRequestCallback<T> callback, String cacheMode) {
        if (easyCall.isExecuted()) {
            EasyLog.e("EasyAndroid", easyCall + " isExecuted");
            return;
        }
        easyCall.enqueue(new EasyHttpStateCallback<T>() {
            @Override
            public void onResponse(EasyResponse<T> easyResponse) {
                callback.onResponse(easyResponse);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        }, cacheMode);
    }
}
