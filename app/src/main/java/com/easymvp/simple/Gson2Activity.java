package com.easymvp.simple;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.easymvp.R;
import com.easymvp.simple.pojo.PagingResult;
import com.easymvp.simple.pojo.PriceInfo;
import com.easymvp.simple.pojo.QfangResult;

import java.util.List;

import cc.easyandroid.EasyToast;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkContract;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkPresenter;
import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkUseCase;
import cc.easyandroid.easyclean.httpextend.mvp.repository.EasyWorkRepository;


public class Gson2Activity extends Activity implements EasyWorkContract.View<QfangResult<PagingResult<PriceInfo>>> {
    EasyWorkPresenter<QfangResult<PagingResult<PriceInfo>>> presenter = new EasyWorkPresenter(new EasyWorkUseCase(new EasyWorkRepository()));
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_string);
        textView = (TextView) findViewById(R.id.text);
        presenter.attachView(this);
//        EasyCall<QfangResult<PagingResult<PriceInfo>>> easyCall = HttpUtils.creatGetCall(this,url, TypeUtils.newInstance(this).getViewType());
//        presenter.execute(new EasyWorkUseCase.RequestValues("测试1", easyCall, CacheMode.LOAD_CACHE_ELSE_NETWORK));
    }

    String url = "http://hk.qfang.com/qfang-api/mobile/common/query/querySalePriceCondition";


    @Override
    public void onStart(Object presenterId) {
        EasyToast.showShort(getApplicationContext(), "onStart");
        String ddd = (String) presenterId;
        System.out.println("cgp onStart tag=" + ddd);
    }

    @Override
    public void onError(Object presenterId, Throwable e) {
        textView.setText("出错" + e.getMessage());
        EasyToast.showShort(getApplicationContext(), "onError" + e.getMessage());
        System.out.println("cgp onError tag=" + presenterId);
    }

    @Override
    public void onSuccess(Object presenterId, QfangResult<PagingResult<PriceInfo>> results) {
        List<PriceInfo> lists = results.getData().getList();
        textView.setText(presenterId.toString() + "==" + lists);
        System.out.println("cgp deliverResult tag=" + presenterId);
        ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
