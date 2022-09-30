package com.example.bible;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bible.adapters.BooksAdapter;
import com.example.bible.networking.BibleService;
import com.example.bible.pojos.Book;
import com.example.bible.pojos.BookResponses;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ArrayList<Book> booksArray = new ArrayList<>();

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

        Call<BookResponses>  listBibles =  service.listBibles();

        listBibles.enqueue(new Callback<BookResponses>() {
            @Override
            public void onResponse(Call<BookResponses> call, Response<BookResponses> response) {
                if(response.isSuccessful())
                {
                    BookResponses bibleResponse = response.body();

                    if(bibleResponse.getData() != null)
                    {
                        for(Book book : bibleResponse.getData())
                        {
                            booksArray.add(book);
                            Log.d(TAG, "onResponse: id"+ book.name);
                        }
                   //https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
                        BooksAdapter adapter = new BooksAdapter(MainActivity.this, booksArray);
                        listView.setAdapter(adapter);
                        //setting the adapter

                    } else {
                        Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<BookResponses> call, Throwable t) {
                Log.d(TAG, t.getMessage());

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d(TAG, "onItemClick:"+view.getId());
//                Log.d(TAG, "onItemClick: "+i);
//
//            }
//        });

    }
}