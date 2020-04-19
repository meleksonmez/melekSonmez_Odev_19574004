package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    TextView sensorList = null;
    private SensorManager mSensorManager;
    private Sensor lightSensor;
    private Sensor accelerometer;
    private SensorEventListener listenerLight;
    private SensorEventListener listenerAccelerometer;
    ConstraintLayout sensorLayout;
    float x, y, z, last_x, last_y, last_z;
    long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorList = (TextView) findViewById(R.id.sensorList_textView);
        sensorList.setVisibility(View.GONE);
        sensorLayout = (ConstraintLayout) findViewById(R.id.sensorLayout);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> mList= mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for (int i = 1; i < mList.size(); i++) {
            sensorList.setVisibility(View.VISIBLE);
            sensorList.append("\n" + mList.get(i).getName() + "\n");
        }

        lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(lightSensor != null) {
            listenerLight = new SensorEventListener() {
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    //Toast.makeText(SensorActivity.this, "accuracy changed!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSensorChanged(SensorEvent event) {
                    int val = (int) event.values[0];
                    int grayShade = 0;
                    if (val < 10000) grayShade = 255;
                    sensorList.setTextColor(Color.rgb(255 - grayShade, 255 - grayShade, 255 - grayShade));
                    sensorList.setBackgroundColor(Color.rgb(grayShade, grayShade, grayShade));
                    sensorLayout.setBackgroundColor(Color.rgb(grayShade, grayShade, grayShade));
                }
            };
            mSensorManager.registerListener(listenerLight, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }

        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(accelerometer != null) {
            listenerAccelerometer = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    last_x = x; last_y = y; last_z = z;
                    x = event.values[0];
                    y = event.values[1];
                    z = event.values[2];
                    if(((last_x + last_y + last_z) - (x + y + z) != 0)){
                        lastTime = event.timestamp;
                    }
                    else {
                        if((event.timestamp - lastTime)/100 > 99999999){
                            Toast.makeText(SensorActivity.this, "App is closing..", Toast.LENGTH_SHORT).show();
                            mSensorManager.unregisterListener(this);
                            finishAffinity();
                        }
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };

            mSensorManager.registerListener(listenerAccelerometer, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
