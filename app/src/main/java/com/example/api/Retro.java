package com.example.api;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Retro {

    @GET("random")
    Call<Dogs> getDogs();

    @GET("breeds/list")
    Call<DogsList> getList();

    @GET("breed/{name}/images")
    Call<DogsList> getImages(@Path("name") String name);
}
