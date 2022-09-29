package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bible.adapters.VersesAdapter;
import com.example.bible.networking.BibleChaptersService;
import com.example.bible.pojos.BibleChaptersData;
import com.example.bible.pojos.BibleChaptersResponses;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BibleVersesActivity extends AppCompatActivity {
    private static final String TAG = "BibleVersesActivity";
    ListView verseListView;
    TextView verseTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_verses);
        verseListView=findViewById(R.id.versesListView);
        verseTextView=findViewById(R.id.versesTextView);
        String chapterId = getIntent().getStringExtra("chapter-id");

        if(chapterId == null)
        {
            Toast.makeText(this, "Chapter id is null", Toast.LENGTH_SHORT).show();
        }


//        bibleListView=findViewById(R.id.bibleListView);
        verseListView=findViewById(R.id.versesListView);

        ArrayList<BibleChaptersData> verseArray = new ArrayList<>();
        VersesAdapter<String> verseAdapter = new VersesAdapter<String>(this, android.R.layout.simple_list_item_1, verseArray);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.scripture.api.bible")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        BibleChaptersService service = (BibleChaptersService) retrofit.create(BibleChaptersService.class);

        Call<BibleChaptersResponses> listChapters = service.listChapters(chapterId);
        listChapters.enqueue(new Callback<BibleChaptersResponses>() {
            @Override
            public void onResponse(Call<BibleChaptersResponses> call, Response<BibleChaptersResponses> response) {
                if (response.isSuccessful()) {
                    BibleChaptersResponses bibleChaptersResponses = response.body();
                    for (BibleChaptersData bibleChaptersData : bibleChaptersResponses.getData()) {
                        verseArray.add(bibleChaptersData);
                    }
                    verseListView.setAdapter(verseAdapter);
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