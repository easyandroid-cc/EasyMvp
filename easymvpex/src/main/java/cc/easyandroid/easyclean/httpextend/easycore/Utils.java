/*
 * Copyright (C) 2012 Square, Inc.
 * Copyright (C) 2007 The Guava Authors
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

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cc.easyandroid.easyclean.httpextend.call.EasyCall;
import cc.easyandroid.easylog.EasyLog;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Source;

public class Utils {
    public static <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null)
            return;
        try {
            closeable.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Replace a {@link  } with an identical copy whose body is backed by
     * a {@link Buffer} rather than a {@link Source}.
     */
    public static ResponseBody readBodyToBytesIfNecessary(final ResponseBody body) throws IOException {
        if (body == null) {
            return null;
        }

        BufferedSource source = body.source();
        Buffer buffer = new Buffer();
        buffer.writeAll(source);
        source.close();

        return ResponseBody.create(body.contentType(), body.contentLength(), buffer);
    }

    public static boolean writeResponseBodyToDisk(ResponseBody body, File file,EasyCall call) {
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    if(call.isCancel()){
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    EasyLog.e("downfile", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
