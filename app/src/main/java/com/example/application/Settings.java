package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private EditText signature, age, weight, height;
    private RadioButton male, female;
    private Switch appMode;
    private Button save;
    private ConstraintLayout settingsLayout;

    public static final String MyPreferences = "MyPrefs";
    public static final String signatureKey = "signatureKey";
    public static final String genderKey = "genderKey";
    public static final String heightKey = "heightKey";
    public static final String weightKey = "weightKey";
    public static final String ageKey = "ageKey";
    public static final String appModeKey = "appModeKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        signature = (EditText) findViewById(R.id.signature_editText);
        age = (EditText) findViewById(R.id.age_editText);
        weight = (EditText) findViewById(R.id.weight_editText);
        height = (EditText) findViewById(R.id.height_editText);
        male = (RadioButton) findViewById(R.id.male_radioButton);
        female = (RadioButton) findViewById(R.id.female_radioButton);
        appMode = (Switch) findViewById(R.id.appMode_switch);
        save = (Button) findViewById(R.id.settingsSave_button);
        settingsLayout = (ConstraintLayout)findViewById(R.id.settingsLayout);

        sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        signature.setText(sharedPreferences.getString(signatureKey, ""));
        age.setText(sharedPreferences.getString(ageKey, ""));
        signature.setText(sharedPreferences.getString(signatureKey, ""));
        signature.setText(sharedPreferences.getString(signatureKey, ""));

        if(sharedPreferences.getString(appModeKey,"").equalsIgnoreCase("ON")) {
            settingsLayout.setBackgroundColor(Color.WHITE);
            appMode.setChecked(true);
        }
        else {
            settingsLayout.setBackgroundColor(Color.DKGRAY);
            appMode.setChecked(false);
        }

        if(sharedPreferences.getString(genderKey,"").equalsIgnoreCase("Male")) {
            male.setChecked(true); female.setChecked(false);
        }
        else {
            male.setChecked(false); female.setChecked(true);
        }

        appMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(appMode.isChecked())
                    settingsLayout.setBackgroundColor(Color.WHITE);
                else
                    settingsLayout.setBackgroundColor(Color.DKGRAY);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender, switchValue = "";

                if (male.isChecked()) {
                    gender = "Male";
                } else if (female.isChecked()) {
                    gender = "Female";
                } else {
                    gender = "";
                }

                switchValue = appMode.isChecked() ? "ON" : "OFF";

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(signatureKey, signature.getText().toString());
                editor.putString(ageKey, age.getText().toString());
                editor.putString(weightKey, weight.getText().toString());
                editor.putString(heightKey, height.getText().toString());
                editor.putString(genderKey, gender);
                editor.putString(appModeKey, switchValue);

                editor.commit();

                Toast.makeText(Settings.this, "Saved", Toast.LENGTH_LONG).show();
            }
        });

    }
}
