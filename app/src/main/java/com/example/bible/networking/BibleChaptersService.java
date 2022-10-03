package com.example.bible.networking;

import com.example.bible.ReUsableComponents;
import com.example.bible.pojos.BibleChaptersResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BibleChaptersService {
    @Headers("api-key: 8364ed0d6d4561cff4c4d879eeebb59e")
    @GET("/v1/bibles/de4e12af7f28f599-01/books/{bookId}/chapters")
    Call<BibleChaptersResponses> listChapters(@Path("bookId") String bookId);
    //https://howtodoinjava.com/retrofit2/query-path-parameters/
}
