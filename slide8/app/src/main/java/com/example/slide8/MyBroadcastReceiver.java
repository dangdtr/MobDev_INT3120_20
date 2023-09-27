package com.example.slide8;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.SearchManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {


    private final static int REQUEST_ENABLE_BT = 1;


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("API123", "" + intent.getAction());

        if (intent.getAction().equals("com.example.ACTION_MY_EVENT")) {
            Toast.makeText(context, "ACTION_MY_EVENT is received", Toast.LENGTH_LONG).show();
            String message = intent.getStringExtra("message");
            MainActivity.getInstace().updateTheTextView(message);

        }
        if (intent.getAction().equals("com.example.SEARCH_EVENT")) {
            Intent intent_search = new Intent(Intent.ACTION_WEB_SEARCH);
            intent_search.putExtra(SearchManager.QUERY, intent.getStringExtra("search"));
            context.startActivity(intent_search);
        }
    }

}
