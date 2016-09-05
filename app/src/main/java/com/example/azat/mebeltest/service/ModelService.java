package com.example.azat.mebeltest.service;

import com.example.azat.mebeltest.modelEntity.Act;
import com.example.azat.mebeltest.modelEntity.Calculation;
import com.example.azat.mebeltest.modelEntity.News;
import com.example.azat.mebeltest.modelEntity.Order;
import com.example.azat.mebeltest.modelEntity.Profile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;


public interface ModelService {
    @GET("orders")
    Observable<List<Order>> getOrders();

    @GET("users/profile")
    Observable<Profile> getProfile();

    @GET("calculations")
    Observable<ArrayList<Calculation>> getCalculations();

    @POST("request-acts")
    Observable<Act> requestActs();

    @GET("news")
    Observable<List<News>> getNews();

    @FormUrlEncoded
    @PUT("orders/rename/{id}")
    Observable<Order> changeNameOrder(@Path("id") int id, @Field("title") String newTitle);
}
