package com.example.net.api;

import com.example.net.protocol.TokenRespEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TokenApi {

    @FormUrlEncoded
    @POST("token")
    Call<TokenRespEntity> getToken(@Field("grant_type") String type, @Field("username") String name, @Field("password") String pwd);


//    @Headers({Config.NEW_URLHEADER_KEY+":"+Config.NEW_URLHEADER_VALUE})
//    @POST("/login")
//    Call<TokenRespEntity> getTest();

}
