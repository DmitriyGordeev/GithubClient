package com.example.app.app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.util.List;

public interface Client {

    @GET("/users/{user}/repos")
    Call<List<Repo>> repos(@Path("user") String user);

}
