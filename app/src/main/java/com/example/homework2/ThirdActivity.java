package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Beer> beers;
    private String data;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        recyclerView = findViewById(R.id.recyclerView_beer);
        textView = findViewById(R.id.textView_result);
        beers = new ArrayList<>();

        Intent intent = getIntent();
//        data = intent.getStringExtra("filtered_data");
//        textView.setText(data);


        JSONObject beersJSON = null;
        try {
            beersJSON = new JSONObject(loadJSONFromAsset("beer.json"));
//            beersJSON = new JSONObject(data);
            JSONArray beersArray = beersJSON.getJSONArray("Beers");
//            JSONArray nameArray = new JSONArray(data);
//            JSONObject nameObj = nameArray.getJSONObject(0);
//            if ()
            for (int i = 0; i < beersArray.length(); i++) {
                JSONObject beerObject = beersArray.getJSONObject(i);
                Beer beer = new Beer(beerObject.getString("name"),
                        beerObject.getString("description"),
                        beerObject.getString("image_url"));
                beers.add(beer);
            }
            BeerAdapter adapter = new BeerAdapter(beers);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    private String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
