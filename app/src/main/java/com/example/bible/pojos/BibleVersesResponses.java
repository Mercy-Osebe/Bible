package com.example.bible.pojos;

import java.util.List;

import retrofit2.Call;

public class BibleVersesResponses {
    List<BibleVersesData> data;

    public List<BibleVersesData> getData() {
        return data;
    }

    final public void setData(List<BibleVersesData> data) {
        this.data = data;
    }

}
