package com.easymvp.simple;

import android.app.Activity;
import android.os.Bundle;

import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkContract;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkPresenter;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkUseCase;
import cc.easyandroid.easyclean.httpextend.mvp.repository.EasyWorkRepository;


public class ThreadActivity extends Activity implements EasyWorkContract.View<String> {
    EasyWorkPresenter<String> presenter = new EasyWorkPresenter<>(new EasyWorkUseCase<String>(new EasyWorkRepository()));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_thread);
        presenter.attachView(this);
    }

//    @Override
//    public EasyCall<String> onCreateCall(Object presenterId, Bundle bundle) {
//
//        return new EasyThreadCall<String>(new PresenterLoader<String>() {
//            @Override
//            public String loadInBackground() throws Exception {
//                return "测试";
//            }
//        });
//    }

    @Override
    public void onStart(Object presenterId) {

    }


    public void onCompleted(Object presenterId) {

    }

    @Override
    public void onError(Object presenterId, Throwable e) {

    }

    @Override
    public void onSuccess(Object tag, String results) {
        deliverResult(tag, results);
        onCompleted(tag);
    }


    public void deliverResult(Object presenterId, String results) {
        System.out.println();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
