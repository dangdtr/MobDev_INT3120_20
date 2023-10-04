package com.example.slide11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnPlay, btnPause;
    private static final String TAG = "MainActivity";

    private MyBoundService myBoundService;
    private boolean isBound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MyBoundService.LocalBinder binder = (MyBoundService.LocalBinder) service;
            myBoundService = binder.getService();
            isBound = true;
            Log.d(TAG, "Service connected");
            Toast.makeText(getApplicationContext(), "Service connected", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
            Log.d(TAG, "Service disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        startService(new Intent(this, BackgroundService.class));

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, MusicForegroundService.class));
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, MusicForegroundService.class));

            }
        });

        Intent svc=new Intent(this, MusicForegroundService.class);
        startService(svc);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to MyBoundService
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }

    public void onButtonClick(View view) {
        if (isBound) {
            // Call a method from the MyBoundService
            String message = myBoundService.getHelloMessage();
            TextView textView = findViewById(R.id.textView);
            textView.setText(message);
        }
    }

}