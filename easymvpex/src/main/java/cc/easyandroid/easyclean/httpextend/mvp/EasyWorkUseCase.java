/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.easyandroid.easyclean.httpextend.mvp;

import cc.easyandroid.easyclean.UseCase;
import cc.easyandroid.easyclean.httpextend.easycore.EasyException;
import cc.easyandroid.easyclean.httpextend.mvp.repository.EasyWorkDataSource;
import cc.easyandroid.easyclean.httpextend.mvp.repository.EasyWorkRepository;
import cc.easyandroid.easyclean.httpextend.easycore.EAResult;
import cc.easyandroid.easyclean.httpextend.call.EasyCall;
import cc.easyandroid.easyclean.httpextend.easycore.EasyResponse;
import cc.easyandroid.easylog.EasyLog;


/**
 * EasyHttp用例
 */
public class EasyWorkUseCase<T> extends UseCase<EasyWorkUseCase.RequestValues, EasyWorkUseCase.ResponseValue<T>> {

    private volatile EasyCall<T> lastEasyCall;//记录最后一个easycall
    public final EasyWorkDataSource mEasyWorkDataSource;

    private void cancelRequest() {
        if (lastEasyCall != null && !lastEasyCall.isCancel()) {
            lastEasyCall.cancel();
        }
    }

    @Override
    public void cancle() {
        cancelRequest();
    }

    public EasyWorkUseCase(EasyWorkDataSource easyWorkDataSource) {
        mEasyWorkDataSource = easyWorkDataSource;
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        EasyCall<T> easyCall = values.getEasyCall();
        synchronized (this) {
            if (easyCall == lastEasyCall) {
                EasyLog.e("EasyAndroid this EasyCall is execute");
                return;
            }
            cancle();
            /**
             * 请求后每次记住easycall，防止重复调用，第二次进来会检测之前的是否完成，如果没有就调用cancelRequest取消之前的请求
             */
            lastEasyCall = easyCall;
        }
        mEasyWorkDataSource.executeRequest(easyCall, new EasyWorkRepository.HttpRequestCallback<T>() {
            @Override
            public void onResponse(EasyResponse<T> easyResponse) {
                T t = easyResponse != null ? easyResponse.body() : null;
                String defaultMessage = easyResponse != null ? easyResponse.message() : "";//"服务器或网络异常";
                EasyLog.e("EasyAndroid tag=%1$s t=%2$s", values.getTag(), t);
                if (t == null) {
                    onFailure(new EasyException(defaultMessage));
                    return;
                } else if (t instanceof EAResult) {
                    EAResult kResult = (EAResult) t;
                    if (!kResult.isSuccess()) {
                        String errorMessage = kResult.getEADesc();
                        onFailure(new EasyException(errorMessage, kResult.getEACode()));
                        return;
                    }
                }
                getUseCaseCallback().onSuccess(new EasyWorkUseCase.ResponseValue<>(values.getTag(), t));
            }

            @Override
            public void onFailure(Throwable t) {
                getUseCaseCallback().onError(t);
            }
        }, values.getCacheMode());
    }

    public static final class RequestValues<T> implements UseCase.RequestValues {
        private final EasyCall<T> mEasyCall;
        private final Object mTag;
        private final String mCacheMode;

        public RequestValues(Object tag, EasyCall<T> easyCall, String cacheMode) {
            this.mTag = tag;
            this.mEasyCall = easyCall;
            this.mCacheMode = cacheMode;
        }

        public Object getTag() {
            return mTag;
        }

        public EasyCall<T> getEasyCall() {
            return mEasyCall;
        }

        public String getCacheMode() {
            return mCacheMode;
        }
    }

    public static final class ResponseValue<T> implements UseCase.ResponseValue {

        private final T data;
        private final Object tag;

        public ResponseValue(Object tag, T data) {
            this.data = data;
            this.tag = tag;
        }

        public Object getTag() {
            return tag;
        }

        public T getData() {
            return data;
        }
    }
}
