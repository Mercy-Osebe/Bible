package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bible.networking.BibleChaptersService;
import com.example.bible.pojos.BibleChaptersData;
import com.example.bible.pojos.BibleChaptersResponses;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BibleChaptersActivity extends AppCompatActivity {
    private static final String TAG = "BibleChaptersActivity";
    ListView bibleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_chapters);
        bibleListView=findViewById(R.id.bibleListView);
        ArrayList<String> chaptersArray = new ArrayList<>();
        ArrayAdapter<String> chaptersArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chaptersArray);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.scripture.api.bible")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        BibleChaptersService service = (BibleChaptersService) retrofit.create(BibleChaptersService.class);

        Call<BibleChaptersResponses> listChapters = service.listChapters();
        listChapters.enqueue(new Callback<BibleChaptersResponses>() {
            @Override
            public void onResponse(Call<BibleChaptersResponses> call, Response<BibleChaptersResponses> response) {
                if (response.isSuccessful()) {
                    BibleChaptersResponses bibleChaptersResponses = response.body();
                    for (BibleChaptersData bibleChaptersData : bibleChaptersResponses.getData()) {
                        chaptersArray.add(bibleChaptersData.getName());
                    }

                    bibleListView.setAdapter(chaptersArrayAdapter);
                }

            }

            @Override
            public void onFailure(Call<BibleChaptersResponses> call, Throwable t) {
                Log.d(TAG, t.getMessage());

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}