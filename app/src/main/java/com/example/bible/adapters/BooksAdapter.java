package com.example.bible.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bible.BibleChaptersActivity;
import com.example.bible.R;
import com.example.bible.pojos.Book;

import java.util.ArrayList;

public class BooksAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> books;
    private Context coontext;
    public BooksAdapter(@NonNull Context context, ArrayList<Book> books)  {
        super(context, 0, books);
        this.books = books;
        this.coontext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book bk = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }

        RelativeLayout container = (RelativeLayout) convertView.findViewById(R.id.container);
        TextView tvBookName = (TextView) convertView.findViewById(R.id.tvBookName);
        // Populate the data into the template view using the data object
        tvBookName.setText(bk.getName());

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(coontext, bk.getId(), Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(coontext, BibleChaptersActivity.class);
                intent.putExtra("book-id", bk.getId());
                coontext.startActivity(intent);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }


}
