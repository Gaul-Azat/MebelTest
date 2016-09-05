package com.example.azat.mebeltest.service;

import com.example.azat.mebeltest.utilsEntity.LoginForm;
import com.example.azat.mebeltest.utilsEntity.TokenEntity;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


public interface AuthService {

    @POST("users/login")
    Observable<TokenEntity> login(@Body LoginForm form);

}
