package com.example.bible;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        ArrayList<String> booksArray=new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.scripture.api.bible")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        BibleService service = retrofit.create(BibleService.class);

        Call<BibleResponses> listBibles =  service.listBibles();

        listBibles.enqueue(new Callback<BibleResponses>() {
            @Override
            public void onResponse(@NonNull Call<BibleResponses> call, @NonNull Response<BibleResponses> response) {
                if(response.isSuccessful())
                {
                    BibleResponses bibleResponse = response.body();

                    assert bibleResponse != null;
                    for(Bible bible: bibleResponse.getData())
                    {
                        booksArray.add(bible.getName());
                    }

                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,booksArray);

                    listView.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onFailure(Call<BibleResponses> call, Throwable t) {
                Log.d(TAG, t.getMessage());

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.scripture.api.bible")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                BibleChaptersService service = retrofit.create(BibleChaptersService.class);

//                Call<BibleResponses> listChapters =  service.listChapters();

                listBibles.enqueue(new Callback<BibleResponses>() {
                    @Override
                    public void onResponse(@NonNull Call<BibleResponses> call, @NonNull Response<BibleResponses> response) {
                        if(response.isSuccessful())
                        {
                            BibleResponses bibleResponse = response.body();

                            assert bibleResponse != null;
                            for(Bible bible: bibleResponse.getData())
                            {
                                booksArray.add(bible.getName());
                            }

                            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,booksArray);

                            listView.setAdapter(arrayAdapter);
                        }

                    }

                    @Override
                    public void onFailure(Call<BibleResponses> call, Throwable t) {
                        Log.d(TAG, t.getMessage());

                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });




    }
}