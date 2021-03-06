package com.example.app.app;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Client {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );


    @Headers("Accept: application/json")
    @GET("user")
    Call<User> user(@Query("access_token") String token);


    @Headers("Accept: application/json")
    @GET("user/repos")
    Call<List<Repo>> repos(@Query("access_token") String token);


    @Headers("Accept: application/json")
    @GET("/repos/{owner}/{repo_name}/commits")
    Call<List<Commit>> commits(@Path("owner") String owner,
                               @Path("repo_name") String repoName,
                               @Query("access_token") String token);

    @Headers("Accept: application/json")
    @GET("/repos/{owner}/{repo_name}/commits")
    Call<okhttp3.ResponseBody> commitsRaw(@Path("owner") String owner,
                               @Path("repo_name") String repoName,
                               @Query("access_token") String token);


}
