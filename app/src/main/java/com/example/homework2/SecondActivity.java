package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import cz.msebera.android.httpclient.Header;

public class SecondActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> userList;

    private Button button_second;

    private EditText editText_search;
    private EditText editTextDate_from;
    private EditText editTextDate_to;

    private static final String api_url = "https://api.punkapi.com/v2/beers/";
    AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        userList = new ArrayList<HashMap<String, String>>();

        editText_search = findViewById(R.id.editText_search);
        editTextDate_from = findViewById(R.id.editTextDate_from);
        editTextDate_to = findViewById(R.id.editTextDate_to);

        Intent intent = getIntent();

        button_second = findViewById(R.id.button_second);

        button_second.setOnClickListener(v -> {
            launchNextActivity(v);
        });
    }

    private void launchNextActivity(View v) {
        client.addHeader("Accept", "application/json");
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("api response", new String(responseBody));
                try {
                    JSONArray array = new JSONArray(new String(responseBody));
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject beerObject = array.getJSONObject(i);
                        String name = beerObject.optString("name");
                        String brewed_date = beerObject.optString("first_brewed");

                        String content = editText_search.getText().toString();
                        String startDate = editTextDate_from.getText().toString();
                        String endDate = editTextDate_to.getText().toString();

                        Date start = new SimpleDateFormat("MM/yyyy", Locale.ENGLISH).parse(startDate);
                        Date end = new SimpleDateFormat("MM/yyyy", Locale.ENGLISH).parse(endDate);
                        Date first_brewed = new SimpleDateFormat("MM/yyyy", Locale.ENGLISH).parse(brewed_date);


                        if (content.equals(name) || (Objects.requireNonNull(start).compareTo(first_brewed) < 0) || (Objects.requireNonNull(end).compareTo(first_brewed) > 0)) {
                            HashMap<String, String> user = new HashMap<String, String>();
                            user.put("name", name);
                            userList.add(user);
                            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                            intent.putExtra("filtered_data", userList.toString());
                            Log.d("added", userList.toString());
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String(responseBody));
            }
        });


    }



}

