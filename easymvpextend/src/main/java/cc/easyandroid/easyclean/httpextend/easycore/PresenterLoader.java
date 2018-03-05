package cc.easyandroid.easyclean.httpextend.easycore;

public interface PresenterLoader<T> {
    T loadInBackground() throws Exception;
}
