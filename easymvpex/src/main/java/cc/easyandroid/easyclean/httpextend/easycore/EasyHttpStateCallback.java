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

/**
 * Communicates responses from a server or offline requests. One and only one method will be
 * invoked in response to a given request.
 * <p>
 * EasyHttpStateCallback methods are executed using the {@link EasyHttpStateCallback} callback executor. When none is
 * specified, the following defaults are used:
 * <ul>
 * <li>Android: Callbacks are executed on the application's main (UI) thread.</li>
 * <li>JVM: Callbacks are executed on the background thread which performed the request.</li>
 * </ul>
 *
 * @param <T> expected response type
 */
public interface EasyHttpStateCallback<T> {
  /** Successful HTTP easyResponse. */
  void onResponse(EasyResponse<T> easyResponse);

  /** Invoked when a network or unexpected exception occurred during the HTTP request. */
  void onFailure(Throwable t);

}
