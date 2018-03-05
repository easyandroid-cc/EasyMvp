package cc.easyandroid.easyclean.presentation.presenter;

import cc.easyandroid.easyclean.presentation.view.IEasyView;

/**
 * 超级接口
 *
 * @param <V>
 */
public interface EasyIPresenter<V extends IEasyView> {

    void cancel();

    void attachView(V view);

    void detachView();
}
