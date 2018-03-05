package cc.easyandroid.easyclean.presentation.presenter;

import cc.easyandroid.easyclean.presentation.view.IEasyView;

/**
 * @param <V>
 * @Override public void onResume() {
 * super.onResume();
 * //Bind view to the presenter which will signal for the presenter to load the task.
 * mPresenter.attachView(this);
 * }
 * @Override public void onPause() {
 * mPresenter.detachView();
 * super.onPause();
 * }
 */

public class EasyBasePresenter<V extends IEasyView> implements EasyIPresenter<V> {
    private V mEasyView;

    @Override
    public void cancel() {
        onCancel();
    }

    protected boolean isViewAttached() {
        return mEasyView != null;
    }

    @Override
    public void attachView(V view) {//建议在onResume中调用
        this.mEasyView = view;
        onAttachView(view);
    }

    protected V getView() {
        return mEasyView;
    }

    @Override
    public void detachView() {//建议在onPause中调用
        mEasyView = null;
        onDetachView();

    }


    protected void onAttachView(V view) {

    }

    protected void onDetachView() {

    }

    protected void onCancel() {

    }

}
