package cc.easyandroid.easyclean.httpextend.easycore;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by Administrator on 2016/5/3.
 */
public class EasyExecutor {

    static final String THREAD_PREFIX = "EasyAndroid-";
    static final String IDLE_THREAD_NAME = THREAD_PREFIX + "Idle";

    static Executor defaultExecutor() {
        return Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable r) {
                return new Thread(new Runnable() {
                    @Override
                    public void run() {
                        android.os.Process.setThreadPriority(THREAD_PRIORITY_BACKGROUND);
                        r.run();
                    }
                }, IDLE_THREAD_NAME);
            }
        });
    }

    static final Executor mainExecutor = new MainThreadExecutor();
    static final Executor threadExecutor = defaultExecutor();

    static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable r) {
            handler.post(r);
        }
    }

    public static Executor getMainExecutor() {
        return mainExecutor;
    }

    public static Executor getThreadExecutor() {
        return threadExecutor;
    }
}
