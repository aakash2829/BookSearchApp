package com.aakash.android.booksearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button searchButton = (Button) findViewById(R.id.search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText getText = (EditText) findViewById(R.id.getquery);
                Intent bookActivityIntent = new Intent(view.getContext(), BooksActivity.class);
                bookActivityIntent.putExtra("queryText", getText.getText().toString());
                startActivity(bookActivityIntent);
            }
        });

    }

}
