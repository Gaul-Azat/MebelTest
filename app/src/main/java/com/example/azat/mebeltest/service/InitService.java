package com.example.azat.mebeltest.service;

import com.example.azat.mebeltest.utilsEntity.InitEntity;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface InitService {

    @POST("app/init")
    Observable<Void> initApp(@Body InitEntity auth);



}
