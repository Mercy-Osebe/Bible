package com.example.bible.networking;

import com.example.bible.pojos.BibleChaptersResponses;
import com.example.bible.pojos.BibleVersesResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BibleVersesService {
    @Headers("api-key: 8364ed0d6d4561cff4c4d879eeebb59e")
    ///v1/bibles/{bibleId}/chapters/{chapterId}/verses
    @GET("/v1/bibles/de4e12af7f28f599-01/chapters/{chapterId}/verses")
    Call<BibleVersesResponses> listVerses(@Path("chapterId") String chapterId);
}
