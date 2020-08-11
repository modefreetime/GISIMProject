package com.example.net;

import android.text.TextUtils;

import com.example.common.LogUtils;
import com.example.net.api.TokenApi;
import com.example.net.common.Config;
import com.example.net.protocol.TokenRespEntity;
import com.example.storage.core.StorageManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {


    private RetrofitManager() {
        initRetrofit();
    }

    private volatile static RetrofitManager retrofitManager = null;
    private Retrofit retrofit;

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }


    //初始化retrofit
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(createOkhttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

                .build();
    }
    //okhttp
    private OkHttpClient createOkhttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.SECONDS)
                .connectTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .addNetworkInterceptor(createNetWorkInterceptor())
                .addInterceptor(tokenInterceptor())
                .build();


        return client;
    }
    //http日志拦截器
    private Interceptor createNetWorkInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }


    /**
     * token拦截器
     *
     * @return
     */
    private Interceptor tokenInterceptor() {
        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //获取本地Token
                String localToken = StorageManager.getInstance().get("token");
                if (!TextUtils.isEmpty(localToken)) {
                    return resetRequest(request, localToken, chain);
                }
                Response response = chain.proceed(request);

                if (checkHttpCode401(response)) {
                    String token = requestToken();
                    if (TextUtils.isEmpty(token)) {
                        return response;
                    }
                    //TODO:保存Token 到SP
                    StorageManager.getInstance().save("token", token);

                    return resetRequest(request, token, chain);
                }


                return response;
            }
        };
        return interceptor;
    }

    private Response resetRequest(Request request, String token, Interceptor.Chain chain) {
        Request.Builder newBuilder = request.newBuilder().addHeader("Authorization", "bearer " + token);

        Request newRequest = newBuilder.build();
        try {
            return chain.proceed(newRequest);
        } catch (IOException e) {
            LogUtils.d(e.getMessage());
        }
        return null;
    }

    private String requestToken() {
        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenRespEntity> tokenService = tokenApi.getToken("password", Config.AUTH_CODE, "");
        try {
            retrofit2.Response<TokenRespEntity> result = tokenService.execute();
            if (result != null && result.body() != null) {
                return result.body().getAccess_token();
            }
        } catch (IOException e) {

        }
        return "";
    }
    private boolean checkHttpCode401(Response proceed) {
        if (proceed == null) {
            return false;
        }
        if (proceed.code() == 401) {
            return true;
        } else {
            return false;
        }
    }
}
