package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {

    private TextView textView_final;
    private TextView textView_finald;
    private String name;
    private String description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        textView_final = findViewById(R.id.textView_final);
        textView_finald = findViewById(R.id.textView_finald);

//        Intent intent = getIntent();
//        name = intent.getStringExtra("beer_name");
//        textView_final.setText(name);
//
//        description = intent.getStringExtra("beer_details");
//        textView_finald.setText(description);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("beer_name");
        description = bundle.getString("beer_details");

        textView_final.setText(name);
        textView_finald.setText(description);
    }
}
