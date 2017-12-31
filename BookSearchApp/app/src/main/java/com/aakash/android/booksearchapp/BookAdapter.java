package com.aakash.android.booksearchapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aakas on 12/13/2017.
 */

public class BookAdapter extends ArrayAdapter<Books> {

    String thumbnail;

    public BookAdapter(Context context, ArrayList<Books> book) {
        super(context, 0, book);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list, parent, false);
        }

        Books currentBook = getItem(position);
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        titleTextView.setText(currentBook.getTitle());

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author);
        String a = currentBook.getAuthor();
        String newStr = a.substring(1, a.length() - 1);
        authorTextView.setText(newStr);

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.publish_date);
        dateTextView.setText(currentBook.getDate());

        ImageView imageUrlView = (ImageView) listItemView.findViewById(R.id.thumbnail);
        Picasso.with(getContext()).load(currentBook.getImageUrl()).into(imageUrlView);

        return listItemView;
    }


}
