package com.easymvp.simple;

/**
 * http工具類，根據需要自己添加
 */
public class HttpUtils {

//    public static <T> EasyCall<T> creatPostCall(String url, Bundle bundle, EasyWorkPresenter<T> presenter) {
//        okhttp3.Request request = OKHttp3RequestFactory.createPostRequest(url, EasyUtils.BundleToMap(bundle));
//        return EasyHttpUtils.getInstance().executeHttpRequestToCall(request, presenter.getDeliverResultType());
//    }
//
//    public static <T> EasyCall<T> creatPostCall(String url, Bundle bundle, EasyWorkPresenter<T> presenter, Map<String, String> header) {
//        okhttp3.Request request = OKHttp3RequestFactory.createPostRequest(url, header, EasyUtils.BundleToMap(bundle));
//        return EasyHttpUtils.getInstance().executeHttpRequestToCall(request, presenter.getDeliverResultType());
//    }
//
//    /**
//     * post请求 上传图片
//     *
//     * @param url       地址
//     * @param bundle    参数
//     * @param presenter
//     * @param files
//     * @param header
//     * @param <T>
//     * @return
//     */
//    public static <T> EasyCall<T> creatPostFileCall(String url, Bundle bundle, EasyWorkPresenter<T> presenter, Map<String, File> files, Map<String, String> header) {
//        okhttp3.Request request = OKHttp3RequestFactory.createMultipartRequest(url, header, EasyUtils.BundleToMap(bundle), files);
//        return EasyHttpUtils.getInstance().executeHttpRequestToCall(request, presenter.getDeliverResultType());
//    }
//
//    /**
//     * post请求
//     *
//     * @param url
//     * @param paras     参数
//     * @param presenter
//     * @param <T>
//     * @return
//     */
//    public static <T> EasyCall<T> creatPostCall(String url, Map<String, String> paras, EasyWorkPresenter<T> presenter) {
//        okhttp3.Request request = OKHttp3RequestFactory.createPostRequest(url, paras);
//        return EasyHttpUtils.getInstance().executeHttpRequestToCall(request, presenter.getDeliverResultType());
//    }
//
//    /**
//     * get请求
//     *
//     * @param url
//     * @param presenter
//     * @param <T>
//     * @return
//     */
//    public static <T> EasyCall<T> creatGetCallNoCache(String url, EasyWorkPresenter<T> presenter) {
//        okhttp3.Request request = OKHttp3RequestFactory.createGetRequest(url);
//        return EasyHttpUtils.getInstance().executeHttpRequestToCall(request, presenter.getDeliverResultType());
//    }
//
//    /**
//     * 先网络后缓存
//     *
//     * @param url
//     * @param presenter
//     * @param <T>
//     * @return
//     */
//    public static <T> EasyCall<T> creatGetCall(Context context,String url, EasyWorkPresenter<T> presenter) {
//        HashMap<String, String> header = new HashMap<>();
////        header.put("Cache-Mode", CacheMode.LOAD_CACHE_ELSE_NETWORK);
//        header.put("Cache-Mode", CacheMode.LOAD_NETWORK_ELSE_CACHE);
//        header.put("Cache-Control", "max-age=10");
//        okhttp3.Request request = OKHttp3RequestFactory.createGetRequest(url, header);
//        return EasyHttpUtils.getInstance( ).executeHttpRequestToCall(request, presenter.getDeliverResultType());
////        return EasyHttpUtils.getInstance().executeHttpRequestToCall(request, presenter.getDeliverResultType());
//    }
//
//    /**
//     * 先网络后缓存
//     *
//     * @param url
//     * @param type
//     * @param <T>
//     * @return
//     */
//    public static <T> EasyCall<T> creatGetCall(Context context,String url, Type type) {
//        HashMap<String, String> header = new HashMap<>();
////        header.put("Cache-Mode", CacheMode.LOAD_CACHE_ELSE_NETWORK);
//        header.put("Cache-Mode", CacheMode.LOAD_NETWORK_ELSE_CACHE);
//        header.put("Cache-Control", "max-age=10");
//        okhttp3.Request request = OKHttp3RequestFactory.createGetRequest(url, header);
//        return EasyHttpUtils.getInstance( ).executeHttpRequestToCall(request, type);
////        return EasyHttpUtils.getInstance().executeHttpRequestToCall(request, type);
//    }
//
//
//    /**
//     * 先缓存后网络
//     *
//     * @param url
//     * @param presenter
//     * @param <T>
//     * @return
//     */
//    public static <T> EasyCall<T> creatGetCallFirstCache(String url, EasyWorkPresenter<T> presenter) {
//        HashMap<String, String> header = new HashMap<>();
//        header.put("Cache-Mode", CacheMode.LOAD_CACHE_ELSE_NETWORK);
//        header.put("Cache-Control", "max-age=340000");
//        okhttp3.Request request = OKHttp3RequestFactory.createGetRequest(url, header);
//        return EasyHttpUtils.getInstance().executeHttpRequestToCall(request, presenter.getDeliverResultType());
//    }
//
//    /**
//     * 上传文件
//     *
//     * @param url
//     * @param header
//     * @param params
//     * @param files
//     * @param presenter
//     * @param <T>
//     * @return
//     */
//    public static <T> EasyCall<T> creatGetCallUpLoadCall(String url, Map<String, String> header, Map<String, String> params, Map<String, File> files, EasyWorkPresenter<T> presenter) {
//        okhttp3.Request request = OKHttp3RequestFactory.createMultipartRequest(url, header, params, files);
//        return EasyHttpUtils.getInstance().executeHttpRequestToCall(request, presenter.getDeliverResultType());
//    }
//
//    /**
//     * 上传文件，有进度显示
//     *
//     * @param url
//     * @param header
//     * @param params
//     * @param files
//     * @param presenter
//     * @param listener
//     * @param <T>
//     * @return
//     */
//    public static <T> EasyCall<T> creatGetCallUpLoadCall(String url, Map<String, String> header, Map<String, String> params, Map<String, File> files, EasyWorkPresenter<T> presenter, final ProgressListener listener) {
//        okhttp3.Request request = OKHttp3RequestFactory.createMultipartRequest(url, header, params, files, listener);
//        return EasyHttpUtils.getInstance().executeHttpRequestToCall(request, presenter.getDeliverResultType());
//    }

//    /**
//     * 下载文件，带进度
//     *
//     * @param url
//     * @param header
//     * @param listener 进度监听接口
//     * @return
//     */
//    public static EasyCall<OkHttpDownLoadEasyCall.DownLoadResult> creatGetDownLoadCall(Context context, String url, Map<String, String> header, File file, final ProgressListener listener) {
//        okhttp3.Request request = OKHttp3RequestFactory.createGetRequest(url, header);
//        OkHttpClient client = EasyHttpUtils.getInstance( ).getOkHttpClient().newBuilder().addNetworkInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Response originalResponse = chain.proceed(chain.request());
//                return originalResponse.newBuilder()
//                        .body(new ProgressResponseBody(originalResponse.body(), listener))
//                        .build();
//            }
//        }).build();
//
//        return EasyHttpUtils.getInstance().executeHttpRequestToDownLoadCall(client, request, file);
//    }

}
