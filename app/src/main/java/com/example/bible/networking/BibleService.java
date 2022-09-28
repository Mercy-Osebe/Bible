package com.example.bible.networking;

import com.example.bible.pojos.BookResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface BibleService {


    //    @GET("/v1/bibles/"+ReUsableComponents.bible_id+"/books")
    @Headers("api-key: 97519447e1b0672ce68311f1c61d7c59")
    @GET("/v1/bibles/de4e12af7f28f599-01/books")
    Call<BookResponses> listBibles();
}
