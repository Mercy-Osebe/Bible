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
//import com.example.bible.networking.VersesService;
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
        String versesId=getIntent().getStringExtra("verse-id");
        System.out.println(chapterId);
        if(chapterId == null)
        {
            Toast.makeText(this, "chapter id is null", Toast.LENGTH_SHORT).show();
        }
//        if(versesId == null)
//        {
//            Toast.makeText(this, "verses id is null", Toast.LENGTH_SHORT).show();
//        }

        verseListView=findViewById(R.id.versesListView);
//        verseTextView=findViewById(R.id.versesTextView);
        ArrayList<BibleVersesData> verseArray = new ArrayList<>();
        ArrayList<VersesStringData> verseStringArray = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.scripture.api.bible")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BibleVersesService service=(BibleVersesService)retrofit.create(BibleVersesService.class);
        VersesStringService versesService=(VersesStringService)retrofit.create(VersesStringService.class);

        Call<BibleVersesResponses> listVerses=service.listVerses(chapterId);
//        Call<VersesStringResponse> listStringVerses=versesService.listStringVerses(versesId);

        listVerses.enqueue(new Callback<BibleVersesResponses>() {
            @Override
            public void onResponse(Call<BibleVersesResponses> call, Response<BibleVersesResponses> response) {
                if (response.isSuccessful()) {
                    BibleVersesResponses bibleVersesResponses = response.body();
                    if(bibleVersesResponses.getData() != null){
                        for(BibleVersesData bibleVerses : bibleVersesResponses.getData()){
                            verseArray  .add(bibleVerses);
                        }
                        VersesAdapter versesAdapter=new VersesAdapter(BibleVersesActivity.this,verseArray);
                        verseListView.setAdapter(versesAdapter);


                    } else Toast.makeText(BibleVersesActivity.this, "No data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BibleVersesResponses> call, Throwable t) {
                Log.d(TAG, t.getMessage());

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
//        listStringVerses.enqueue(new Callback<VersesStringResponse>() {
//            @Override
//            public void onResponse(Call<VersesStringResponse> call, Response<VersesStringResponse> response) {
//                if (response.isSuccessful()) {
//                    VersesStringResponse versesStringResponse = response.body();
//                    if(versesStringResponse.getData() != null){
//                        for(VersesStringData verseString : versesStringResponse.getData()){
//                            verseStringArray.add(verseString);
//                        }
//                        VersesString versesString=new VersesString(BibleVersesActivity.this,verseStringArray);
//                        verseListView.setAdapter(versesString);
//
//
//                    } else Toast.makeText(BibleVersesActivity.this, "No data", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<VersesStringResponse> call, Throwable t) {
//                Log.d(TAG, t.getMessage());
//
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });

        //Extracting the verse strings.
                    //
                    //        VersesService versesService=(VersesService)retrofit.create(VersesService.class);
                    //        Call<VersesContentResponses> verses=versesService.verses(chapterId);
                    //
                    //
                    //        verseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    //            @Override
                    //            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //
                    //            }
                    //        });



    }
}