package cc.easyandroid.easyclean.httpextend.easycache;

import android.content.Context;

import java.io.UnsupportedEncodingException;

import cc.easyandroid.easyclean.httpextend.easycore.EAResult;
import cc.easyandroid.easylog.EasyLog;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * 缓存http的响应体
 */
public class EasyHttpCache {
    public static final int DEFAULTCACHEDURATION = 60 * 60 * 24 * 5;//默认缓存时长 5天  单位是秒
    final Cache cache;

    /**
     * 使用前先进行初始化
     *
     * @param context context
     */
    public EasyHttpCache(Context context) {
        cache = new DiskBasedCache(CacheUtils.getDiskCacheDir(context.getApplicationContext(), "volleycache"));
        cache.initialize();
    }

    public void put(Request request, Object object, byte[] data) throws UnsupportedEncodingException {
        parseCache(request, object, data, "application/json; charset=UTF-8");
    }

    public ResponseBody get(Request request) {
        checkNull(cache);
        Cache.Entry entry = cache.get(request.url().toString());// 充缓存中获取entry
        if (entry == null) {
            return null;
        }
        if (entry.isExpired()) {// 缓存过期了
            return null;
        }
        if (entry.data != null) {// 如果有数据就使用缓存
            MediaType contentType = MediaType.parse(entry.mimeType);
            byte[] bytes = entry.data;
            try {
                return ResponseBody.create(contentType, bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void clearCache() {
        checkNull(cache);
        cache.clear();
    }

    //将结果保存到cache中
    private void parseCache2(Request request, Object object, byte[] data, String mimeType) throws UnsupportedEncodingException {
        okhttp3.CacheControl cacheControl = request.cacheControl();
        String cache_time = request.header("Cache-Duration");//缓存时长
        long maxAgeSeconds = parseSeconds(cache_time, 60 * 60 * 24 * 5);
        if (cacheControl != null) {
            if (!cacheControl.noCache() && !cacheControl.noStore()) {
                if (chechCanSave(object)) {
                    long now = System.currentTimeMillis();
                    long maxAge = cacheControl.maxAgeSeconds();
                    long softExpire = now + maxAge * 1000;
                    EasyLog.d("easycache When long: %1$s秒", (softExpire - now) / 1000 + "");
                    Cache.Entry entry = new Cache.Entry();
                    entry.softTtl = softExpire;
                    entry.ttl = entry.softTtl;
                    entry.mimeType = mimeType;
                    entry.data = data;
                    checkNull(cache);
                    cache.put(request.url().toString(), entry);
                }
            }
        }
    }

    //将结果保存到cache中
    private void parseCache(Request request, Object object, byte[] data, String mimeType) throws UnsupportedEncodingException {
        String cache_time = request.header("Cache-Duration");//缓存时长
        long maxAgeSeconds = parseSeconds(cache_time, DEFAULTCACHEDURATION);
        if (chechCanSave(object)) {
            long now = System.currentTimeMillis();
            long maxAge = maxAgeSeconds;
            long softExpire = now + maxAge * 1000;
            EasyLog.d("easycache When long: %1$s秒", (softExpire - now) / 1000 + "");
            Cache.Entry entry = new Cache.Entry();
            entry.softTtl = softExpire;
            entry.ttl = entry.softTtl;
            entry.mimeType = mimeType;
            entry.data = data;
            checkNull(cache);
            cache.put(request.url().toString(), entry);
        }
    }
    public static int parseSeconds(String value, int defaultValue) {
        try {
            long seconds = Long.parseLong(value);
            if (seconds > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (seconds < 0) {
                return 0;
            } else {
                return (int) seconds;
            }
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    boolean chechCanSave(Object object) {
        if (object != null && object instanceof EAResult) {
            EAResult kResult = (EAResult) object;
            if (kResult.isSuccess()) {
                return true;
            }
        }
        if (object != null && object instanceof String) {
            return true;
        }
        return false;
    }

    private void checkNull(Cache cache) {
        if (cache == null) {
            throw new IllegalArgumentException("Please call first initialize method");
        }

    }
}
