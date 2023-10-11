package com.example.slide12;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView textViewAccuracy;
    private TextView textViewSensorData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        // Initialize views
        textViewAccuracy = findViewById(R.id.textViewAccuracy);
        textViewSensorData = findViewById(R.id.textViewSensorData);

        // Initialize SensorManager and Sensor
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Check if the accelerometer sensor is available
        if (mSensor != null) {
            // Register the SensorEventListener with SENSOR_DELAY_GAME
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
        } else {
            textViewAccuracy.setText("Accelerometer not available");
            textViewSensorData.setText("");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Update the accuracy TextView when accuracy changes
        textViewAccuracy.setText("Accuracy: " + accuracy);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Update the sensor data TextView when sensor data changes
        float[] values = event.values;
        textViewSensorData.setText("Sensor Data: X: " + values[0] + ", Y: " + values[1] + ", Z: " + values[2]);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the SensorEventListener when the activity is paused
        mSensorManager.unregisterListener(this);
    }
}
