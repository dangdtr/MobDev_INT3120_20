package com.example.slide12;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;

    private Button mButton, mButtonPhone, mButtonCamera, mButtonBlue;
    private TextView mTextViewWifi;
    private TextView infoTextView;
    private BroadcastReceiver wifiReceiver;

    private Camera camera;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String service = Context.WIFI_SERVICE;
        WifiManager wifi = (WifiManager) getSystemService(service);

        mButton = findViewById(R.id.button_next);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, SensorActivity.class);
                startActivity(intent);
            }
        });

        mButtonPhone = findViewById(R.id.button_phone);
        mButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:55512345"));
                smsIntent.putExtra("sms_body", "Press send to send me");
                startActivity(smsIntent);

//                SmsManager smsManager = SmsManager.getDefault();
//                String sendTo = "5551234";
//                String myMessage = "Android supports programmatic SMS messaging!";
//                smsManager.sendTextMessage(sendTo, null, myMessage, null, null);

            }
        });

        mButtonCamera = findViewById(R.id.button_camera);
        mButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Camera2Activity.class);
                startActivity(intent);
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(intent, TAKE_PICTURE);
//                }
            }
        });

        mButtonBlue = findViewById(R.id.button_bluetooth);
        mButtonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
                startActivity(intent);
            }
        });


        mTextViewWifi = findViewById(R.id.textView_wifi);
        infoTextView = findViewById(R.id.infoTextView);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            Log.e("SENSOR", "yes");
        } else {
            Log.e("SENSOR", "no");
        }


        // Initialize and register the broadcast receiver
        wifiReceiver = new ConnectivityReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiReceiver, intentFilter);
        displayPhoneInfo();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiReceiver);
    }

    private void displayPhoneInfo() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        String networkOperator = telephonyManager.getNetworkOperatorName();
        String simOperator = telephonyManager.getSimOperatorName();
        int dataState = telephonyManager.getDataState();

        String info =
                "Network Operator: " + networkOperator + "\n" +
                        "SIM Operator: " + simOperator + "\n" +
                        "Data State: " + dataState;

        infoTextView.setText(info);
    }



    public class ConnectivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action != null && action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                checkConnectivity(context);
            }
        }

        private void checkConnectivity(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                showToast(context, "WiFi is connected");
                mTextViewWifi.setText("WiFi is connected");
            } else {
                showToast(context, "WiFi is disconnected");
                mTextViewWifi.setText("WiFi is disconnected");

            }
        }

        private void showToast(Context context, String message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }


    public class WifiBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action != null) {
                switch (action) {
                    case WifiManager.WIFI_STATE_CHANGED_ACTION:
                        handleWifiStateChanged(context, intent);
                        break;
                    case WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION:
                        handleSupplicantConnectionChanged(context, intent);
                        break;
                    case WifiManager.NETWORK_STATE_CHANGED_ACTION:
                        handleNetworkStateChanged(context, intent);
                        break;
                    case WifiManager.RSSI_CHANGED_ACTION:
                        handleRssiChanged(context, intent);
                        break;
                }
            }
        }

        private void handleWifiStateChanged(Context context, Intent intent) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
            String wifiStateText = getWifiStateText(wifiState);
            Toast.makeText(context, "Wi-Fi state changed: " + wifiStateText, Toast.LENGTH_SHORT).show();
        }

        private void handleSupplicantConnectionChanged(Context context, Intent intent) {
            boolean connected = intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false);
            String connectionStatus = connected ? "Connected" : "Disconnected";
            Toast.makeText(context, "Supplicant connection changed: " + connectionStatus, Toast.LENGTH_SHORT).show();
        }

        private void handleNetworkStateChanged(Context context, Intent intent) {
            NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (networkInfo != null && networkInfo.isConnected()) {
                Toast.makeText(context, "Connected to a network", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Disconnected from the network", Toast.LENGTH_SHORT).show();
            }
        }

        private void handleRssiChanged(Context context, Intent intent) {
            int rssi = intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI, 0);
            Toast.makeText(context, "RSSI changed: " + rssi, Toast.LENGTH_SHORT).show();
        }

        private String getWifiStateText(int wifiState) {
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    return "Disabled";
                case WifiManager.WIFI_STATE_DISABLING:
                    return "Disabling";
                case WifiManager.WIFI_STATE_ENABLED:
                    return "Enabled";
                case WifiManager.WIFI_STATE_ENABLING:
                    return "Enabling";
                case WifiManager.WIFI_STATE_UNKNOWN:
                default:
                    return "Unknown";
            }
        }
    }

}