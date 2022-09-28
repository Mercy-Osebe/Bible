package com.example.bible.pojos;

import java.util.List;

public class BibleResponses {
    static List<Bible> data;

    public List<Bible> getData() {
        return data;
    }

    public void setData(List<Bible> data) {
        BibleResponses.data = data;
    }
}
