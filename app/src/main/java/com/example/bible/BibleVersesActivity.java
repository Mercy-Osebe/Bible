package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bible.adapters.VersesAdapter;
import com.example.bible.networking.BibleVersesService;
//import com.example.bible.networking.VersesService;
import com.example.bible.pojos.BibleVersesData;
import com.example.bible.pojos.BibleVersesResponses;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BibleVersesActivity extends AppCompatActivity {
    private static final String TAG = "BibleVersesActivity";
    ListView verseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_verses);
        verseListView = findViewById(R.id.versesListView);
        String chapterId = getIntent().getStringExtra("chapter-id");

        if (chapterId == null) {
            Toast.makeText(this, "chapter id is null", Toast.LENGTH_SHORT).show();
        } else {
            verseListView = findViewById(R.id.versesListView);
            ArrayList<BibleVersesData> verseArray = new ArrayList<>();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.scripture.api.bible")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            BibleVersesService service = (BibleVersesService) retrofit.create(BibleVersesService.class);
            Call<BibleVersesResponses> listVerses = service.listVerses(chapterId);

            listVerses.enqueue(new Callback<BibleVersesResponses>() {
                @Override
                public void onResponse(Call<BibleVersesResponses> call, Response<BibleVersesResponses> response) {
                    if (response.isSuccessful()) {
                        BibleVersesResponses bibleVersesResponses = response.body();
                        if (bibleVersesResponses.getData() != null) {
                            for (BibleVersesData bibleVerses : bibleVersesResponses.getData()) {
                                verseArray.add(bibleVerses);
                            }
                            VersesAdapter versesAdapter = new VersesAdapter(BibleVersesActivity.this, verseArray);
                            verseListView.setAdapter(versesAdapter);


                        } else
                            Toast.makeText(BibleVersesActivity.this, "No data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BibleVersesResponses> call, Throwable t) {
                    Log.d(TAG, t.getMessage());

                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }


    }
}
