package com.aakash.android.booksearchapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by aakas on 12/13/2017.
 */

public class BooksLoader extends AsyncTaskLoader<List<Books>> {
    private static final String LOG_TAG = BooksLoader.class.getName();

    private String mUrl;
    private HttpQuery QueryUtils;

    public BooksLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Books> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<Books> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}
