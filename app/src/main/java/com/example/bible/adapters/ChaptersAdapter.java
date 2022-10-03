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
import com.example.bible.BibleVersesActivity;
import com.example.bible.R;
import com.example.bible.pojos.BibleChaptersData;
import com.example.bible.pojos.Book;

import java.util.ArrayList;

public class ChaptersAdapter extends ArrayAdapter<BibleChaptersData> {
    private ArrayList<BibleChaptersData> bibleChapters;
    private Context chaptersContext;

    public ChaptersAdapter(@NonNull Context context,ArrayList<BibleChaptersData> bibleChapters) {
        super(context, 0, bibleChapters);
        this.bibleChapters=bibleChapters;
        this.chaptersContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BibleChaptersData chapt=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chapter, parent, false);
        }
        RelativeLayout chaptersContainer = (RelativeLayout) convertView.findViewById(R.id.chapterContainer);
        TextView tvChapterName = (TextView) convertView.findViewById(R.id.tvChapterName);
        tvChapterName.setText(chapt.getId());
        chaptersContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(chaptersContext, chapt.getId(), Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(chaptersContext, BibleVersesActivity.class);
                intent.putExtra("chapter-id", chapt.getId());
                chaptersContext.startActivity(intent);

            }
        });
        return convertView;

    }
}
