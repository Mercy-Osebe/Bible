package com.example.bible.pojos;

import java.util.List;

public class VersesStringResponse {
    List<VersesStringData> data;

    public List<VersesStringData> getData() {
        return data;
    }

    final public void setData(List<VersesStringData> data) {
        this.data = data;
    }
}
