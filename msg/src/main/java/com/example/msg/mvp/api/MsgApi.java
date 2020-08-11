package com.example.msg.mvp.api;

import com.example.msg.mvp.entity.msgEntity;
import com.example.net.protocol.BaseRespEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MsgApi {


    @GET("api/Chat/getChatMsg")
    Observable<BaseRespEntity<List<msgEntity>>> get(@Query("user1")String u1,@Query("user2")String u2,@Query("page")String page,@Query("pagesize")String ps);
//
//    @POST("api/Chat/addChatMsg")
//    Observable<BaseRespEntity<String>>

}
