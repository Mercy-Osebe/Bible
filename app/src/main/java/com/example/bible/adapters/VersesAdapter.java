package com.example.bible.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.bible.MainActivity;
import com.example.bible.R;
import com.example.bible.pojos.BibleChaptersData;
import com.example.bible.pojos.BibleVersesData;
import com.example.bible.pojos.Book;

import java.util.ArrayList;

public class VersesAdapter extends ArrayAdapter<BibleVersesData>  {
    private static final String TAG = "VersesAdapter";
    private ArrayList<BibleVersesData> bibleVerses;
    private Context versesContext;

    public VersesAdapter(@NonNull Context context, ArrayList<BibleVersesData> bibleVerses) {
        super(context,0, bibleVerses);
        this.versesContext=context;
        this.bibleVerses=bibleVerses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BibleVersesData ver = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_verse, parent, false);
        }

        RelativeLayout container = (RelativeLayout) convertView.findViewById(R.id.versesContainer);
        TextView tvVerseName = (TextView) convertView.findViewById(R.id.tvVersesName);
        // Populate the data into the template view using the data object
        tvVerseName.setText(ver.id);
        TextView versesString=(TextView)convertView.findViewById(R.id.versesTextView);


        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(versesContext, ver.getId(), Toast.LENGTH_SHORT).show();

//                versesString.setText(ver.reference);

//
                Intent intent = new Intent(versesContext, MainActivity.class);
                intent.putExtra("verse-id", ver.id);
                System.out.println(ver.id);
//                versesContext.startActivity(intent);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
