package com.example.bible.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bible.R;
import com.example.bible.pojos.VersesStringData;

import java.util.ArrayList;

public class VersesString extends ArrayAdapter {
    private ArrayList<VersesStringData> bibleStringVerses;
    private Context versesContext;


    public VersesString(@NonNull Context context, ArrayList<VersesStringData> bibleStringVerses) {
        super(context, 0, bibleStringVerses);
        this.bibleStringVerses = bibleStringVerses;
        this.versesContext = context;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        VersesStringData verses= (VersesStringData) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chapter, parent, false);
        }
        RelativeLayout chaptersContainer = (RelativeLayout) convertView.findViewById(R.id.versesStringContainer);
        TextView tvVersesString = (TextView) convertView.findViewById(R.id.tvVersesString);
        tvVersesString.setText(verses.content);
        chaptersContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(versesContext, verses.content, Toast.LENGTH_SHORT).show();


//                Intent intent = new Intent(chaptersContext, BibleVersesActivity.class);
//                intent.putExtra("chapter-id", chapt.getId());
//                chaptersContext.startActivity(intent);

            }
        });

        return convertView;
    }
}
