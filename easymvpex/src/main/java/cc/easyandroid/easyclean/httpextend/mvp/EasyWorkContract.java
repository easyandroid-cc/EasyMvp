/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.easyandroid.easyclean.httpextend.mvp;

import cc.easyandroid.easyclean.presentation.presenter.EasyIPresenter;
import cc.easyandroid.easyclean.presentation.view.IEasyView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface EasyWorkContract {

    interface View<T> extends IEasyView {

        void onStart(Object tag);

        /**
         * 使用 e.getMessage() 获取信息
         *
         * @param tag  标识
         * @param e 异常
         */
        void onError(Object tag, Throwable e);

        void onSuccess(Object tag, final T results);
    }

    interface Presenter<T> extends EasyIPresenter<View<T>> {

        void execute(EasyWorkUseCase.RequestValues requestValues);

        void reExecute();

    }
}
