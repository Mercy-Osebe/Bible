package com.example.bible;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.example.bible.networking.BibleChaptersService;
import com.example.bible.networking.BibleService;
import com.example.bible.pojos.Bible;
import com.example.bible.pojos.BibleResponses;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ArrayList<String> booksArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.scripture.api.bible")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BibleService service = retrofit.create(BibleService.class);

        Call<BibleResponses>  listBibles =  service.listBibles();

        listBibles.enqueue(new Callback<BibleResponses>() {
            @Override
            public void onResponse( Call<BibleResponses> call,Response<BibleResponses> response) {
                if(response.isSuccessful())
                {
                    BibleResponses bibleResponse = response.body();

                    assert bibleResponse != null;
                    for(Bible bible: bibleResponse.getData())
                    {
                        booksArray.add(bible.getName());
                        Log.d(TAG, "onResponse: id"+bible.getId());
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, booksArray);

                    listView.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<BibleResponses> call, Throwable t) {
                Log.d(TAG, t.getMessage());

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick:"+view.getId());
                Log.d(TAG, "onItemClick: "+i);

            }
        });

        Log.d(TAG, "onCreate: end");
    }
}