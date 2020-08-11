package com.example.user.mvp.api;

import com.example.net.protocol.BaseRespEntity;
import com.example.user.mvp.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserCenterApi {

    @POST("api/User/register")
    Observable<BaseRespEntity<UserEntity>> reg(@Body UserEntity entity);

    @POST("api/User/login")
    Observable<BaseRespEntity<UserEntity>> login(@Body UserEntity entity);
}
