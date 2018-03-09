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
import cc.easyandroid.easyclean.UseCaseHandler;
import cc.easyandroid.easyclean.presentation.presenter.EasyBasePresenter;
import cc.easyandroid.easylog.EasyLog;

/**
 * Listens to user actions from the UI ({@link EasyWorkContract}), retrieves the data and updates
 * the UI as required. view by  attachView(view) method incoming;
 * 将   触发时间--后台任务--返回结果 这样的工作流的一个封装
 * EasyCall 目前有几个实现  OkHttpDownLoadEasyCall，RetrofitCallToDownloadEasyCall，ThreadEasyCall，RetrofitCallToEasyCall
 */
public class EasyWorkPresenter<T> extends EasyBasePresenter<EasyWorkContract.View<T>> implements EasyWorkContract.Presenter<T> {

    protected final UseCaseHandler mUseCaseHandler = UseCaseHandler.getInstance();
    private final EasyWorkUseCase<T> mEasyWorkUseCase;


    public EasyWorkPresenter(EasyWorkUseCase<T> easyWorkUseCase) {
        mEasyWorkUseCase = easyWorkUseCase;
    }

    /**
     * 用户传入RequestValues执行网络请求
     *
     * @param requestValues 用户请求的参数
     */
    @Override
    public void execute(EasyWorkUseCase.RequestValues requestValues) {//这里是执行网络请求
        setRequestValues(requestValues);
        handleRequest(requestValues);
    }

    @Override
    public void reExecute() {
        if (mRequestValues != null) {//EasyCall只能执行一次，so这里必须clone
            EasyWorkUseCase.RequestValues requestValues = new EasyWorkUseCase.RequestValues(mRequestValues.getTag(), mRequestValues.getEasyCall().clone(), mRequestValues.getCacheMode());
            setRequestValues(requestValues);
            handleRequest(mRequestValues);
        } else {
            throw new IllegalArgumentException("must be call setRequestValues(EasyWorkUseCase.RequestValues<T> requestValues) method");
        }
        EasyLog.d("EasyWorkPresenter", "execute");
    }

    private EasyWorkUseCase.RequestValues mRequestValues;

    private void handleRequest(final EasyWorkUseCase.RequestValues requestValues) {
        if (isViewAttached())
            getView().onStart(requestValues.getTag());
        //mEasyWorkUseCase 自己会判断是否有call在运行，如果有，他会自己取消之前的
        mUseCaseHandler.execute(mEasyWorkUseCase, requestValues, new UseCase.UseCaseCallback<EasyWorkUseCase.ResponseValue<T>>() {
            @Override
            public void onSuccess(EasyWorkUseCase.ResponseValue<T> response) {
                if (isViewAttached())
                    getView().onSuccess(requestValues.getTag(), response.getData());
            }

            @Override
            public void onError(Throwable t) {
                if (isViewAttached())
                    getView().onError(requestValues.getTag(), t);
            }
        });
    }

    public void setRequestValues(EasyWorkUseCase.RequestValues requestValues) {
        mRequestValues = requestValues;
    }

//    @Override
//    public void execute() {
//        if (mRequestValues != null) {
//            handleRequest(mRequestValues);
//        } else {
//            throw new IllegalArgumentException("must be call setRequestValues(EasyWorkUseCase.RequestValues<T> requestValues) method");
//        }
//        EasyLog.d("EasyWorkPresenter", "execute");
//    }

    @Override
    protected void onDetachView() {
        cancel();
    }

    @Override
    protected void onCancel() {
        super.onCancel();
        mEasyWorkUseCase.cancle();
    }

    @Override
    protected void onAttachView(EasyWorkContract.View<T> view) {
        super.onAttachView(view);
    }

}
