package com.example.app.app;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Client {

//    @GET("/users/{user}/repos")
//    Call<List<Repo>> repos(@Path("user") String user);

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );

    @GET("https://api.github.com/repos/user/repo")
    @Headers({"Accept: application/json", "Authorization: token {token}"})
    Call<List<Repo>> repos(@Path("token") String accessToken);

}
