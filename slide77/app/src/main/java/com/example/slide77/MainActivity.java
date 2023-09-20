package com.example.slide77;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    private Button buttonNext = null;
    private Button buttonSendMessage = null;
    private EditText editTextFullName = null;
    private TextView textFeedback = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editTextFullName = (EditText) this.findViewById(R.id.editText_fullName);
        this.textFeedback = (TextView) this.findViewById(R.id.textView_feedback);

        this.buttonSendMessage = (Button) this.findViewById(R.id.button_sendMessage);
        this.buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        this.buttonNext = (Button) this.findViewById(R.id.button_next_activity);
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IntentActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sendMessage() {
        String  fullName = this.editTextFullName.getText().toString();
        String message = "Hello, Please say hello me";

        Intent intent = new Intent(this, GreetingActivity.class);
        intent.putExtra("fullName", fullName);
        intent.putExtra("message", message);
        startActivity(intent);
        activityResultLauncher.launch(intent);
//        this.startActivityForResult(intent, MY_REQUEST_CODE);
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        String feedback = result.getData().getStringExtra("feedback");
                        textFeedback.setText(feedback);
                    } else {
                        textFeedback.setText("!?");
                    }
                }
            });


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
//            String feedback = data.getStringExtra("feedback");
//            this.textFeedback.setText(feedback);
//        } else {
//            this.textFeedback.setText("!?");
//        }
//    }

}