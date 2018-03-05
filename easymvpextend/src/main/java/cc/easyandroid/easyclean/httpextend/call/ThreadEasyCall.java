package cc.easyandroid.easyclean.httpextend.call;


import cc.easyandroid.easyclean.httpextend.easycore.EasyExecutor;
import cc.easyandroid.easyclean.httpextend.easycore.EasyHttpStateCallback;
import cc.easyandroid.easyclean.httpextend.easycore.EasyRunnable;
import cc.easyandroid.easyclean.httpextend.easycore.PresenterLoader;
import okhttp3.Request;

/**
 *
 */
public class ThreadEasyCall<T> implements EasyCall<T> {
    protected EasyRunnable easyRunnable;
    private boolean executed; // Guarded by this.
    private volatile boolean canceled;
    private final PresenterLoader<T> loader;

    public ThreadEasyCall(PresenterLoader<T> loader) {
        this.loader = loader;
    }

    @Override
    public void enqueue(EasyHttpStateCallback<T> callback, String tag) {
        synchronized (this) {
            if (executed)
                throw new IllegalStateException("Already enqueue");
            executed = true;
        }
        EasyRunnable originalRunnable = new EasyRunnable(loader, callback);
        this.easyRunnable = originalRunnable;
        EasyExecutor.getThreadExecutor().execute(originalRunnable);
    }

    @Override
    public void cancel() {
        canceled = true;
        if (easyRunnable != null) {
            easyRunnable.cancel();
        }
    }

    @Override
    public boolean isCancel() {
        return canceled;
    }

    @Override
    public EasyCall<T> clone() {
        return new ThreadEasyCall(loader);
    }

    @Override
    public boolean isExecuted() {
        return executed;
    }

    @Override
    public Request request() {//网络请求才用到
        return null;
    }
}
