package com.example.bible.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponses {

    @SerializedName("data")
    private List<Book> data;

    public List<Book> getData() {
        return data;
    }

    public void setData(List<Book> data) {
        this.data = data;
    }
}
