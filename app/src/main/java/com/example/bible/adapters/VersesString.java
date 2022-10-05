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
import androidx.core.text.HtmlCompat;

import com.example.bible.R;
import com.example.bible.StringVersesActivity;
import com.example.bible.pojos.BibleVersesData;
import com.example.bible.pojos.VersesStringData;

import java.util.ArrayList;

public class VersesString extends ArrayAdapter {
    private ArrayList<VersesStringData> bibleStringVerses;
    private Context versesContext;

    public VersesString(@NonNull Context context, ArrayList<VersesStringData> bibleStringVerses) {
        super(context, 0,bibleStringVerses);
        this.bibleStringVerses = bibleStringVerses;
        this.versesContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        VersesStringData verses= bibleStringVerses.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_verse_string, parent, false);
        }
        RelativeLayout versesStringContainer = (RelativeLayout) convertView.findViewById(R.id.versesStringContainer);
        TextView tvVersesString = (TextView) convertView.findViewById(R.id.tvVersesString);
        //converting html to java string.
        Toast.makeText(versesContext, HtmlCompat.fromHtml(verses.content, 0), Toast.LENGTH_SHORT).show();
        tvVersesString.setText(HtmlCompat.fromHtml(verses.content, 0));

        versesStringContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(versesContext, verses.content, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        VersesStringData ver= bibleStringVerses.get(position);
//        return convertView;
//    }


//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        BibleVersesData ver=getItem(position);
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_verse, parent, false);
//        }
//
//        RelativeLayout versesContainer = (RelativeLayout) convertView.findViewById(R.id.versesContainer);
//        TextView tvVerseName = (TextView) convertView.findViewById(R.id.tvVersesName);
//        // Populate the data into the template view using the data object
//        tvVerseName.setText(ver.id);
//
//        versesContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
////                versesString.setText(ver.reference);
//
//                Intent intent = new Intent(versesContext, StringVersesActivity.class);
//                intent.putExtra("verse-id", ver.getId());
//                System.out.println(ver.getId()+"jddjfdfdjfjdsfjdfdd");
//                Toast.makeText(versesContext, ver.getId(), Toast.LENGTH_SHORT).show();
//                versesContext.startActivity(intent);
//            }
//        });
//        // Return the completed view to render on screen
//        return convertView;
}
