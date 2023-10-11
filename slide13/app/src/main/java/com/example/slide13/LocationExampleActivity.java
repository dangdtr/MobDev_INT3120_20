package com.example.slide13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import android.Manifest;

public class LocationExampleActivity extends AppCompatActivity implements LocationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_example);

        // Khởi tạo LocationManager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            // Kiểm tra quyền trước khi yêu cầu vị trí
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // Lắng nghe vị trí có độ chính xác thấp
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
            } else {
                // Yêu cầu quyền nếu chưa có
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // Xử lý thông tin vị trí mới
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        float accuracy = location.getAccuracy();

        // Hiển thị thông tin vị trí
        Toast.makeText(this, "Latitude: " + latitude + ", Longitude: " + longitude + ", Accuracy: " + accuracy, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Xử lý khi trạng thái của nguồn vị trí thay đổi
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Xử lý khi người dùng bật nguồn vị trí
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Xử lý khi người dùng tắt nguồn vị trí
    }
}
