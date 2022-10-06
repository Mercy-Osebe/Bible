package com.example.bible.networking;

import com.example.bible.pojos.VersesStringResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface VersesStringService {
    @Headers("api-key: 8364ed0d6d4561cff4c4d879eeebb59e")
    @GET("/v1/bibles/de4e12af7f28f599-01/verses/{verseId}")
    Call<VersesStringResponse> listStringVerses(@Path("verseId") String verseId);
}
