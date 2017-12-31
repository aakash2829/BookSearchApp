package com.aakash.android.booksearchapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aakas on 12/13/2017.
 */

public class BooksActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Books>> {

    private static final String GOOGLE_BOOK_URL =
            "https://www.googleapis.com/books/v1/volumes?";
    private static final String API_KEY = "AIzaSyABEiMiHd_1TLDZltoxIKgINZ7wUm2l_RI";
    private static final int BOOK_LOADER_ID = 1;
    private BookAdapter mAdapter;
    private TextView mEmptyStateView;
    private boolean isConnected;

    @Override
    public Loader<List<Books>> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri = Uri.parse(GOOGLE_BOOK_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", getIntent().getStringExtra("queryText"));
        uriBuilder.appendQueryParameter("maxResults", "40");
        Log.e("URL", uriBuilder.toString());
        return new BooksLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> books) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        if (!isConnected) {
            mEmptyStateView.setText(R.string.no_internet);
        } else {
            mEmptyStateView.setText(R.string.no_books);
        }

        mAdapter.clear();

        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
        mAdapter.clear();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_books);

        mEmptyStateView = (TextView) findViewById(R.id.empty_view);

        ListView bookListView = (ListView) findViewById(R.id.list);
        bookListView.setEmptyView(mEmptyStateView);
        mAdapter = new BookAdapter(this, new ArrayList<Books>());
        bookListView.setAdapter(mAdapter);

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Books currentBook = mAdapter.getItem(i);
                Uri bookUri = Uri.parse(currentBook.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);
                startActivity(websiteIntent);
            }
        });


        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);

    }
}
