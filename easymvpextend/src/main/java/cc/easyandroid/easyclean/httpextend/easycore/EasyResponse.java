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
package cc.easyandroid.easyclean.httpextend.easycore;

public final class EasyResponse<T> {

    public static <T> EasyResponse<T> success(T body) {
        return new EasyResponse<>(body);
    }

    public static <T> EasyResponse<T> error(int code, String message) {
        EasyResponse easyResponse = new EasyResponse<>(null);
        easyResponse.code = code;
        easyResponse.message = message;
        return easyResponse;
    }

    private EasyResponse(T body) {
        this.body = body;
    }

    private final T body;
    private int code;
    private String message;


    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public T body() {
        return body;
    }

}
