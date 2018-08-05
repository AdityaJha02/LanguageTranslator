package com.example.adityajha.languagetranslator;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Translation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);

        Intent i = getIntent();
        TextView textView = (TextView)findViewById(R.id.translated);
        textView.setText(i.getStringExtra("text"));
    }

}
