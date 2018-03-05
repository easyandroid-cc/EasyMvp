//package com.easymvp.simple;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.TextView;
//
//import com.easymvp.R;
//import com.easymvp.simple.pojo.PagingResult;
//import com.easymvp.simple.pojo.PriceInfo;
//import com.easymvp.simple.pojo.QfangResult;
//
//import java.util.List;
//
//import cc.easyandroid.EasyToast;
//import cc.easyandroid.easyclean.httpextend.call.EasyCall;
//import cc.easyandroid.easyclean.httpextend.mvp.EasyWorkPresenter;
//
//
//public class GsonActivity extends Activity implements ISimpleCallView<QfangResult<PagingResult<PriceInfo>>> {
//    EasyWorkPresenter<QfangResult<PagingResult<PriceInfo>>> presenter = new EasyWorkPresenter<>();
//    TextView textView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_string);
//        textView = (TextView) findViewById(R.id.text);
//        presenter.attachView(this);
//        presenter.execute(null, "测试1");
//        presenter.execute(null, "测试2");
//        presenter.execute(null,"测试3");
//    }
//
//    String url = "http://hk.qfang.com/qfang-api/mobile/common/query/querySalePriceCondition";
//
//    @Override
//    public EasyCall<QfangResult<PagingResult<PriceInfo>>> onCreateCall(Object presenterId, Bundle bundle) {
//        return HttpUtils.creatGetCall(this,url, presenter);
//    }
//
//    @Override
//    public void onStart(Object presenterId) {
//        EasyToast.showShort(getApplicationContext(), "onStart");
//        String ddd= (String) presenterId;
//        System.out.println("cgp onStart tag=" + ddd);
//    }
//
//    @Override
//    public void onCompleted(Object presenterId) {
//        EasyToast.showShort(getApplicationContext(), "onCompleted");
//        System.out.println("cgp onCompleted tag=" + presenterId);
//    }
//
//    @Override
//    public void onError(Object presenterId, Throwable e) {
//        textView.setText("出错" + e.getMessage());
//        EasyToast.showShort(getApplicationContext(), "onError" + e.getMessage());
//        System.out.println("cgp onError tag=" + presenterId);
//    }
//
//    @Override
//    public void deliverResult(Object presenterId, QfangResult<PagingResult<PriceInfo>> results) {
//        List<PriceInfo> lists = results.getData().getList();
//        textView.setText(presenterId.toString());
//        System.out.println("cgp deliverResult tag="+presenterId);
//        ;
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        presenter.detachView();
//    }
//}
