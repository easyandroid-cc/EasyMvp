/*
 * Copyright (C) 2012 Square, Inc.
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
package cc.easyandroid.easyclean.httpextend.easycore;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;


public class EasyRunnable<T> implements Runnable {
    private final EasyHttpStateCallback<T> callback;
    private final PresenterLoader<T> loader;
    private final Executor callbackExecutor = EasyExecutor.getMainExecutor();
    private final AtomicBoolean mCancelled = new AtomicBoolean();

    public EasyRunnable(PresenterLoader<T> loader, EasyHttpStateCallback<T> callback) {
        this.callback = callback;
        this.loader = loader;
    }

    public void cancel() {
        mCancelled.set(true);
    }

    public boolean isCancel() {
        return mCancelled.get();
    }

    @Override
    public final void run() {
        try {
            if (isCancel()) {
                return;
            }
            if (isCancel()) {
                return;
            }
            final T wrapper = loader.loadInBackground();
            if (isCancel()) {
                return;
            }
            callbackExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    if (!isCancel()) {
                        callback.onResponse(EasyResponse.success(wrapper));
                    }
                }
            });
        } catch (Exception e) {
            if (isCancel()) {
                return;
            }
            final Exception handled = e;
            callbackExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    if (!isCancel()) {
                        callback.onFailure(handled);
                    }
                }
            });
        }
    }
}
