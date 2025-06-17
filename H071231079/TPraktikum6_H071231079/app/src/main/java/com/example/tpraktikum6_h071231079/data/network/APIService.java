package com.example.tpraktikum6_h071231079.data.network;


import com.example.tpraktikum6_h071231079.data.response.CharacterResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("character")
    Call<CharacterResponse> getCharacters(@Query("page") int page);
}
