package com.example.slide9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.chromium.net.CronetEngine;
import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

import java.nio.ByteBuffer;

public class WeatherActivityy extends AppCompatActivity {
    private RequestQueue mRequestQueue;

    Button button_load_data;
    TextView textView_data;
    ByteBuffer myBuffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_activityy);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView_data = findViewById(R.id.textView_data);

        button_load_data = findViewById(R.id.button_load_data);
        button_load_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(WeatherActivityy.this);
                String url  = "https://cat-fact.herokuapp.com/facts/";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                textView_data.setText("Response is: " + response.substring(0, 500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView_data.setText("Dont work");
                    }
                });

                queue.add(stringRequest);
                Toast.makeText(WeatherActivityy.this, "Hehe", Toast.LENGTH_LONG);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Move to Main", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }
        if (id == R.id.item2) {
            Intent intent = new Intent(this, InternalStorage.class);
            startActivity(intent);
            Toast.makeText(this, "Move to InternalStorage", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.item3) {
            Intent intent = new Intent(this, ExternalStorage.class);
            startActivity(intent);
            Toast.makeText(this, "Move to ExternalStorage", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.item4) {
            Intent intent = new Intent(this, SQLiteStorage.class);
            startActivity(intent);
            Toast.makeText(this, "Move to SQLiteStorage", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.item5) {
            Intent intent = new Intent(this, WeatherActivityy.class);
            startActivity(intent);
            Toast.makeText(this, "Move to ", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }

}