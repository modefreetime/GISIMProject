package com.example.map.mvp.api;

import com.example.map.mvp.entity.PeopleEntity;
import com.example.net.protocol.BaseRespEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContractApi {

    @GET("api/Friend/getFriends")
    Observable<BaseRespEntity<List<PeopleEntity>>> get(@Query("usercode")String code);

}
