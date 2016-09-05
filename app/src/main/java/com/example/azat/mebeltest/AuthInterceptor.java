package com.example.azat.mebeltest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private String _token;

    public AuthInterceptor(String _token) {
        this._token = _token;
    }

    public void setToken(String _token) {
        this._token = _token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        Request newRequest;
        newRequest=request.newBuilder()
                .addHeader("Authorization","Bearer "+_token)
                .build();
        return chain.proceed(newRequest);
    }
}
