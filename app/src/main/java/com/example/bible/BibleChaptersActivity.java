package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bible.adapters.BooksAdapter;
import com.example.bible.adapters.ChaptersAdapter;
import com.example.bible.networking.BibleChaptersService;
import com.example.bible.networking.BibleService;
import com.example.bible.pojos.BibleChaptersData;
import com.example.bible.pojos.BibleChaptersResponses;
import com.example.bible.pojos.BibleVersesData;
import com.example.bible.pojos.Book;
import com.example.bible.pojos.BookResponses;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BibleChaptersActivity extends AppCompatActivity {
    private static final String TAG = "BibleChaptersActivity";
    GridView chaptersGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_chapters);


//get the onclick intent-from the main activity list view.
        String bookId = getIntent().getStringExtra("book-id");

        if(bookId == null)
        {
            Toast.makeText(this, "Book id is null", Toast.LENGTH_SHORT).show();
        }
        chaptersGridView=findViewById(R.id.chaptersGridView);

        ArrayList<BibleChaptersData> chaptersArray = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.scripture.api.bible")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BibleChaptersService service = (BibleChaptersService) retrofit.create(BibleChaptersService.class);

        Call<BibleChaptersResponses> listChapters = service.listChapters(bookId);

        listChapters.enqueue(new Callback<BibleChaptersResponses>() {
            @Override
            public void onResponse(Call<BibleChaptersResponses> call, Response<BibleChaptersResponses> response) {
                if (response.isSuccessful()) {
                    BibleChaptersResponses bibleChaptersResponses = response.body();
                   if(bibleChaptersResponses.getData() != null){
                       for(BibleChaptersData bibleChapters : bibleChaptersResponses.getData()){
                           chaptersArray.add(bibleChapters);
                       }
                       ChaptersAdapter chaptersArrayAdapter=new ChaptersAdapter(BibleChaptersActivity.this,chaptersArray);
                       chaptersGridView.setAdapter(chaptersArrayAdapter);


                   } else Toast.makeText(BibleChaptersActivity.this, "No data", Toast.LENGTH_SHORT).show();
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