package com.example.slide11;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyBoundService extends Service {

    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    // Public method for clients
    public String getHelloMessage() {
        return "Hello from the Bound Service!";
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
