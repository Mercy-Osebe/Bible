package com.example.bible.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.bible.pojos.BibleChaptersData;

import java.util.ArrayList;

public class VersesAdapter<S> extends ArrayAdapter<BibleChaptersData>  {


    public VersesAdapter(@NonNull Context context, int resource, ArrayList<BibleChaptersData> verseArray) {
        super(context, resource);
    }
}
