package com.easymvp.simple.clean;

import com.easymvp.simple.clean.usecase.DeleteByIdFromDbUseCase;
import com.easymvp.simple.clean.usecase.DeleteDatasFromDbUseCase;
import com.easymvp.simple.clean.usecase.GetDatasFromDbUseCase;
import com.easymvp.simple.clean.usecase.InsertDataFromDbUseCase;
import com.easymvp.simple.clean.usecase.InsertDatasFromDbUseCase;

import cc.easyandroid.easyclean.UseCase;
import cc.easyandroid.easyclean.UseCaseHandler;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkContract;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkPresenter;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkUseCase;
import cc.easyandroid.easyclean.httpextend.mvp.repository.EasyWorkRepository;
import cc.easyandroid.easyclean.presentation.presenter.EasyBasePresenter;
import cc.easyandroid.easysqlite.core.EasyDbObject;

/**
 * Created by cgpllx on 2016/6/3.
 */
public class SimplePresenter1<D1, D2, D3 extends EasyDbObject> extends EasyBasePresenter<SimpleContract.View<D1>> implements SimpleContract.Presenter<D1> {
    private final UseCaseHandler mUseCaseHandler = UseCaseHandler.getInstance();
    EasyWorkPresenter<String> easyWorkPresenter1;
    EasyWorkPresenter<String> easyWorkPresenter2;

    public SimplePresenter1(DeleteByIdFromDbUseCase deleteByIdFromDbUseCase,//
                            DeleteDatasFromDbUseCase deleteDatasFromDbUseCase,//
                            GetDatasFromDbUseCase<D3> getDatasFromDbUseCase,//
                            InsertDataFromDbUseCase<D3> insertDataFromDbUseCase,//
                            InsertDatasFromDbUseCase<D3> insertDatasFromDbUseCase) {
        mDeleteByIdFromDbUseCase = deleteByIdFromDbUseCase;
        mDeleteDatasFromDbUseCase = deleteDatasFromDbUseCase;
        mGetDatasFromDbUseCase = getDatasFromDbUseCase;
        mInsertDataFromDbUseCase = insertDataFromDbUseCase;
        mInsertDatasFromDbUseCase = insertDatasFromDbUseCase;
        easyWorkPresenter1 = new EasyWorkPresenter(new EasyWorkUseCase(new EasyWorkRepository()));
        easyWorkPresenter2 = new EasyWorkPresenter(new EasyWorkUseCase(new EasyWorkRepository()));
        setupPresenter1View();
        setupPresenter2View();
    }

    private final DeleteByIdFromDbUseCase mDeleteByIdFromDbUseCase;
    private final DeleteDatasFromDbUseCase mDeleteDatasFromDbUseCase;
    private final GetDatasFromDbUseCase<D3> mGetDatasFromDbUseCase;
    private final InsertDataFromDbUseCase<D3> mInsertDataFromDbUseCase;
    private final InsertDatasFromDbUseCase<D3> mInsertDatasFromDbUseCase;

    public void exe1(EasyWorkUseCase.RequestValues<D1> requestValues) {
        easyWorkPresenter1.setRequestValues(requestValues);
        easyWorkPresenter1.execute();
    }

    public void exe2(EasyWorkUseCase.RequestValues<D2> requestValues) {
        easyWorkPresenter2.setRequestValues(requestValues);
        easyWorkPresenter2.execute();
    }

    public void exe3(DeleteByIdFromDbUseCase.RequestValues requestValues) {
        mUseCaseHandler.execute(mDeleteByIdFromDbUseCase, requestValues, new UseCase.UseCaseCallback<DeleteByIdFromDbUseCase.ResponseValue>() {
            @Override
            public void onSuccess(DeleteByIdFromDbUseCase.ResponseValue response) {

            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    public void exe4(DeleteDatasFromDbUseCase.RequestValues requestValues) {
        mUseCaseHandler.execute(mDeleteDatasFromDbUseCase, requestValues, new UseCase.UseCaseCallback<DeleteDatasFromDbUseCase.ResponseValue>() {
            @Override
            public void onSuccess(DeleteDatasFromDbUseCase.ResponseValue response) {

            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    public void exe5(GetDatasFromDbUseCase.RequestValues requestValues) {
        mUseCaseHandler.execute(mGetDatasFromDbUseCase, requestValues, new UseCase.UseCaseCallback<GetDatasFromDbUseCase.ResponseValue<D3>>() {
            @Override
            public void onSuccess(GetDatasFromDbUseCase.ResponseValue<D3> response) {

            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    public void exe6(InsertDataFromDbUseCase.RequestValues requestValues) {
        mUseCaseHandler.execute(mInsertDataFromDbUseCase, requestValues, new UseCase.UseCaseCallback<InsertDataFromDbUseCase.ResponseValue>() {
            @Override
            public void onSuccess(InsertDataFromDbUseCase.ResponseValue response) {

            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    public void exe7(InsertDatasFromDbUseCase.RequestValues requestValues) {
        mUseCaseHandler.execute(mInsertDatasFromDbUseCase, requestValues, new UseCase.UseCaseCallback<InsertDatasFromDbUseCase.ResponseValue>() {
            @Override
            public void onSuccess(InsertDatasFromDbUseCase.ResponseValue response) {

            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    private void setupPresenter1View() {
        easyWorkPresenter1.attachView(new EasyWorkContract.View<String>() {
            @Override
            public void onStart(Object tag) {
                getView().setTitle("title");
            }

            @Override
            public void onError(Object tag, Throwable e) {

            }

            @Override
            public void onSuccess(Object tag, String results) {

            }
        });
    }

    private void setupPresenter2View() {
        easyWorkPresenter2.attachView(new EasyWorkContract.View<String>() {
            @Override
            public void onStart(Object tag) {

            }

            @Override
            public void onError(Object tag, Throwable e) {

            }

            @Override
            public void onSuccess(Object tag, String results) {

            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    protected void onDetachView() {
        super.onDetachView();
        easyWorkPresenter1.detachView();
        easyWorkPresenter2.detachView();
    }
}
