package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button mail = (Button) findViewById(R.id.mail_button);
        Button sensors = (Button) findViewById(R.id.sensor_button);
        Button users = (Button) findViewById(R.id.users_button);
        Button settings = (Button) findViewById(R.id.settings_button);

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(MenuActivity.this, SendEmail.class);
                startActivity(mailIntent);
            }
        });

        sensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorIntent = new Intent(MenuActivity.this, SensorActivity.class);
                startActivity(sensorIntent);
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usersIntent = new Intent(MenuActivity.this, UsersActivity.class);
                startActivity(usersIntent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MenuActivity.this, Settings.class);
                startActivity(settingsIntent);
            }
        });

    }
}
