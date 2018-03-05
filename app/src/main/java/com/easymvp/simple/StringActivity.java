package com.easymvp.simple;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.easymvp.R;

import cc.easyandroid.EasyToast;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkContract;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkPresenter;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkUseCase;
import cc.easyandroid.easyclean.httpextend.mvp.repository.EasyWorkRepository;


public class StringActivity extends Activity implements EasyWorkContract.View<String> {
    EasyWorkPresenter<String> presenter = new EasyWorkPresenter<>(new EasyWorkUseCase<String>(new EasyWorkRepository()));
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_string);
        textView = (TextView) findViewById(R.id.text);
        presenter.attachView(this);
//        EasyCall<String> call = HttpUtils.creatGetCall(this, "http://www.baidu.com", String.class);
//        presenter.execute(new EasyWorkUseCase.RequestValues(null, call, null));
    }

//    @Override
//    public EasyCall<String> onCreateCall(Object presenterId, Bundle bundle) {
//        return HttpUtils.creatGetCall(this, "http://www.baidu.com", presenter);
//    }

    @Override
    public void onStart(Object presenterId) {
        EasyToast.showShort(getApplicationContext(), "onStart");
    }


    public void onCompleted(Object presenterId) {
        EasyToast.showShort(getApplicationContext(), "onCompleted");
    }

    @Override
    public void onError(Object presenterId, Throwable e) {
        textView.setText("出错" + e.getMessage());
        EasyToast.showShort(getApplicationContext(), "onError" + e.getMessage());
    }

    @Override
    public void onSuccess(Object tag, String results) {
        deliverResult(tag, results);
        onCompleted(tag);
    }

    public void deliverResult(Object presenterId, String results) {
        textView.setText(results);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
//        presenter.
    }
}
