package com.aakash.android.booksearchapp;

/**
 * Created by aakas on 12/13/2017.
 */

public class Books {

    private String mTitle;
    private String mImageUrl;
    private String mAuthor;
    private String mDate;
    private String mUrl;

    public Books(String imageUrl, String title, String author, String date, String url) {
        mImageUrl = imageUrl;
        mTitle = title;
        mAuthor = author;
        mDate = date;
        mUrl = url;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
