package com.easymvp.simple.core;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cgpllx on 2016/10/26.
 */
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
//        response.networkResponse().
        String cache = request.header("Cache-Time");
        if (cache != null && !cache.equals("")) {//缓存时间不为空
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                            //cache for cache seconds
                    .header("Cache-Control", "max-age=" + cache)
                    .build();
//            response1.body().
            return response1;
        } else {
            return response;
        }
    }
}
