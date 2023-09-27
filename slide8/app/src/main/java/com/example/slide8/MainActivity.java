package com.example.slide8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static MainActivity ins;

    private Button button;
    private Button button_system;
    private BroadcastReceiver msgReceiver ;
    IntentFilter intentFilter;
    IntentFilter intentFilter2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("com.example.ACTION_MY_EVENT");
        intent.putExtra("message", "Hello, this is a broadcast event!");

        msgReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter("com.example.ACTION_MY_EVENT");
        intentFilter2 = new IntentFilter("com.example.SEARCH_EVENT");
//        intentFilter2 = new IntentFilter("android.net.conn.BLUETOOTH_SERVICE");


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBroadcast(intent);
            }
        });

        button_system = (Button) findViewById(R.id.button_system);
        button_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.SEARCH_EVENT");
                intent.putExtra("search", "How to A+ mobile");
//                Intent intent = new Intent("android.net.conn.BLUETOOTH_SERVICE");
                sendBroadcast(intent);
            }
        });


        registerReceiver(msgReceiver, intentFilter);
        registerReceiver(msgReceiver, intentFilter2);


        ins = this;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(msgReceiver);
    }

    public static MainActivity  getInstace(){
        return ins;
    }

    public void updateTheTextView(final String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView textV1 = (TextView) findViewById(R.id.textView);
                textV1.setText(t);
            }
        });
    }

}