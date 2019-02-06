package tig567899.symbilitychallenge;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;

interface ApiInterface {
    @Headers("X-RapidAPI-Key: d59d355e04mshafe59ed2c401756p15dff4jsn3cd076a17fc0")
    @GET("/cards")
    Call<CardList> getData();
}
