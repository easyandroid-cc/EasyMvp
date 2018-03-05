package com.easymvp.simple;

import android.app.Application;

/**
 * app
 */
public class HKQfangApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);
        initEasyAndroid();
    }

    private void initEasyAndroid() {
//        EAConfiguration eaConfiguration = new EAConfiguration.Builder(this).setOkHttpClient(OkHttpClientFactory.getGenericClient(this)).build();
//        EasyHttpUtils.getInstance().init(eaConfiguration);
//        EasyHttpUtils.getInstance().getOkHttpClient().setCookieHandler((new CookieManager(eaConfiguration.getCookieStore(), CookiePolicy.ACCEPT_ALL)));
//        EasyHttpUtils.getInstance().getOkHttpClient().interceptors().add(new Interceptor() {
//            @Override
//            public EasyResponse intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                EasyResponse response = chain.proceed(request);
//                int tryCount = 0;
//                while (!response.isSuccessful() && tryCount < 3) {
//                    Log.d("intercept", "Request is not successful - " + tryCount);
//                    tryCount++;
//                    // retry the request
//                    response = chain.proceed(request);
//                }
//                return response;
//            }
//        });
    }
}
