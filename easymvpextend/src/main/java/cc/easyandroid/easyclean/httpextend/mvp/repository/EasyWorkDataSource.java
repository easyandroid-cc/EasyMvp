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
package cc.easyandroid.easyclean.httpextend.mvp.repository;

import cc.easyandroid.easyclean.httpextend.call.EasyCall;
import cc.easyandroid.easyclean.httpextend.easycore.EasyResponse;


public interface EasyWorkDataSource {

    <T> void executeRequest(EasyCall<T> easyCall, final HttpRequestCallback<T> callback);


    <T> void executeRequest(EasyCall<T> easyCall, final HttpRequestCallback<T> callback, String cacheMode);

    interface HttpRequestCallback<T> {
        void onResponse(EasyResponse<T> easyResponse);

        /**
         * Invoked when a network or unexpected exception occurred during the HTTP request.
         */
        void onFailure(Throwable t);
    }
}
