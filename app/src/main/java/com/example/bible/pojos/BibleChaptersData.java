package com.example.bible.pojos;

import android.content.Intent;

public class BibleChaptersData {
    public  String id;
    public  String bibleId;
    public  String bookId;
    public  String number;
    public  String reference;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBibleId() {
        return bibleId;
    }

    public void setBibleId(String bibleId) {
        this.bibleId = bibleId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
