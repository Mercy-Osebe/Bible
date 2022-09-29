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

import com.example.bible.BibleVersesActivity;
import com.example.bible.R;
import com.example.bible.pojos.BibleChaptersData;
import com.example.bible.pojos.Book;

import java.util.ArrayList;

public class VersesAdapter<S> extends ArrayAdapter<BibleChaptersData>  {
    private ArrayList<BibleChaptersData> chapters;
    private Context coontext;

    public VersesAdapter(@NonNull Context context, int resource, ArrayList<BibleChaptersData> chapters) {
        super(context, 0, chapters);
        this.chapters=chapters;
        this.coontext=coontext;
    }

//    public VersesAdapter(@NonNull Context context, ArrayList<BibleChaptersData> chapters)  {
//            super(context, 0, chapters);
//            this.coontext = context;
//        }
//
//
//
//    public VersesAdapter(BibleVersesActivity context, int simple_list_item_1, ArrayList<S> verseArray) {
//        super();
//    }


    @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        https://stackoverflow.com/questions/9730328/the-getview-method-of-arrayadapter-is-not-getting-called
            BibleChaptersData bk = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chapter, parent, false);
            }

            RelativeLayout container = (RelativeLayout) convertView.findViewById(R.id.chapterContainer);
            TextView tvBibleChaptersDataName = (TextView) convertView.findViewById(R.id.tvChapterName);
            // Populate the data into the template view using the data object
            tvBibleChaptersDataName.setText(bk.getId());

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(coontext, bk.getId(), Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(coontext, BibleVersesActivity.class);
                    intent.putExtra("chapter-id", bk.getId());
                    coontext.startActivity(intent);
                }
            });
            // Return the completed view to render on screen
            return convertView;
        }


}
