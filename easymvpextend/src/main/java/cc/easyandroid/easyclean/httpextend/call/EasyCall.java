/*
 * Copyright (C) 2015 Square, Inc.
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
package cc.easyandroid.easyclean.httpextend.call;

import cc.easyandroid.easyclean.httpextend.easycore.EasyHttpStateCallback;
import okhttp3.Request;

public interface EasyCall<T> {
    void enqueue(EasyHttpStateCallback<T> callback, String tag);//子线程执行

    void cancel();

    boolean isCancel();

    EasyCall<T> clone();

    boolean isExecuted();

    /** The original HTTP request. */
    Request request();
}
