package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bible.adapters.VersesAdapter;
import com.example.bible.adapters.VersesString;
import com.example.bible.networking.BibleVersesService;
import com.example.bible.networking.VersesStringService;
import com.example.bible.pojos.BibleVersesData;
import com.example.bible.pojos.BibleVersesResponses;
import com.example.bible.pojos.VersesStringData;
import com.example.bible.pojos.VersesStringResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StringVersesActivity extends AppCompatActivity {
    private static final String TAG = "StringVersesActivity";
    ListView stringListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_verses);
        String versesId = getIntent().getStringExtra("verse-id");
        if(versesId== null){
            Toast.makeText(this, "null verse id", Toast.LENGTH_SHORT).show();
        }
        else{
            stringListView = findViewById(R.id.stringListView);

            ArrayList<VersesStringData> verseStringArray = new ArrayList<>();
            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl("https://api.scripture.api.bible")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            VersesStringService versesService = (VersesStringService) retrofit2.create(VersesStringService.class);
            Call<VersesStringResponse> listStringVerses = versesService.listStringVerses(versesId);
            listStringVerses.enqueue(new Callback<VersesStringResponse>() {
                @Override
                public void onResponse(Call<VersesStringResponse> call, Response<VersesStringResponse> response) {
                    if (response.isSuccessful()) {
                        VersesStringResponse versesStringResponse = response.body();
                        if (versesStringResponse.getData() != null) {
//                            for (VersesStringData verseString : versesStringResponse.getData()) {
//                                verseStringArray.add(verseString);
//                            }
                            //the object
                            VersesStringData verseString = versesStringResponse.getData();
                            verseStringArray.add(verseString);
                            VersesString versesString = new VersesString(StringVersesActivity.this, verseStringArray);
                            stringListView.setAdapter(versesString);
                        } else
                            Toast.makeText(StringVersesActivity.this, "No data", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<VersesStringResponse> call, Throwable t) {
                    Log.d(TAG, t.getMessage());
                    Log.e(TAG, t.getMessage());
                    System.out.println("nkt");
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


//            verseListView = findViewById(R.id.versesListView);
//            ArrayList<BibleVersesData> verseArray = new ArrayList<>();
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("https://api.scripture.api.bible")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            BibleVersesService service = (BibleVersesService) retrofit.create(BibleVersesService.class);
//            Call<BibleVersesResponses> listVerses = service.listVerses(chapterId);
//
//            listVerses.enqueue(new Callback<BibleVersesResponses>() {
//                @Override
//                public void onResponse(Call<BibleVersesResponses> call, Response<BibleVersesResponses> response) {
//                    if (response.isSuccessful()) {
//                        BibleVersesResponses bibleVersesResponses = response.body();
//                        if (bibleVersesResponses.getData() != null) {
//                            for (BibleVersesData bibleVerses : bibleVersesResponses.getData()) {
//                                verseArray.add(bibleVerses);
//                            }
//                            VersesAdapter versesAdapter = new VersesAdapter(BibleVersesActivity.this, verseArray);
//                            verseListView.setAdapter(versesAdapter);
//
//
//                        } else
//                            Toast.makeText(BibleVersesActivity.this, "No data", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<BibleVersesResponses> call, Throwable t) {
//                    Log.d(TAG, t.getMessage());
//
//                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            });

        }



    }
}