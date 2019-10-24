package com.bibi.wisdom.network.interceptor;
import android.net.Uri;
import android.util.Log;

import com.vondear.rxtool.RxLogTool;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import static com.vondear.rxtool.RxDataTool.stringToLong;
import static java.net.HttpURLConnection.HTTP_NOT_MODIFIED;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static okhttp3.internal.http.StatusLine.HTTP_CONTINUE;

/**
 * @author RedLi
 * @date 2018/3/20
 *
 * 打印网络请求时传输的字段还有返回的json数据
 */

public class LoggerInterceptor implements Interceptor {

    private final static String TAG = LoggerInterceptor.class.getSimpleName();
    private final static int URL_LENGTH = 2;

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request();
        //打印日志
        RequestBody requestBody = newRequest.body();

        String body = null;

        if(requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = Charset.forName("utf-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            body = buffer.readString(charset);
        }
        RxLogTool.e(String.format("发送请求\nmethod：%s\nurl：%s\nheaders: %sbody：%s",
                newRequest.method(), newRequest.url(), newRequest.headers(), body));

        long startNs = System.nanoTime();
        Response response = chain.proceed(newRequest);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        String rBody = null;

        if(hasBody(newRequest,response)) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("utf-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }
            }
            rBody = buffer.clone().readString(charset);
        }

        RxLogTool.e(String.format("收到响应 %s%s %ss\n请求url：%s\n请求body：%s\n响应body：%s",
                response.code(), response.message(), tookMs, response.request().url(), body, rBody));
        return response;
    }


    private boolean hasBody(Request request, Response response) {
        // HEAD requests never yield a body regardless of the response headers.
        if (response.request().method().equals("HEAD")) {
            return false;
        }

        int responseCode = response.code();
        if ((responseCode < HTTP_CONTINUE || responseCode >= 200)
                && responseCode != HTTP_NO_CONTENT
                && responseCode != HTTP_NOT_MODIFIED) {
            return true;
        }

        // If the Content-Length or Transfer-Encoding headers disagree with the
        // response code, the response is malformed. For best compatibility, we
        // honor the headers.
        if (stringToLong(request.headers().get("Content-Length")) != -1
                || "chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return true;
        }

        return false;
    }



}