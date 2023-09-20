package com.example.slide77;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GreetingActivity extends Activity {

    private Button buttonBack = null;
    private TextView textViewMessage = null;
    private String fullName = "";

    private String message = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        textViewMessage = findViewById(R.id.textView_message);


        this.buttonBack = findViewById(R.id.button_back);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        Intent intent = this.getIntent();
        this.fullName = intent.getStringExtra("fullName");

        this.message = intent.getStringExtra("message");
        this.textViewMessage.setText(this.message);
    }
    public void goBack() {
        this.onBackPressed();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        String feedback = "OK, Hello " + this.fullName + ". How are you";
        data.putExtra("feedback", feedback);

        setResult(RESULT_OK, data);
        super.finish();
    }


}