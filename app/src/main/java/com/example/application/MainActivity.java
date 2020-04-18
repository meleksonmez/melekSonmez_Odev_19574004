package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Users> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText unameText = (EditText) findViewById(R.id.username_editText);
        final EditText passwdText = (EditText) findViewById(R.id.password_editText);

        final Button loginButton = (Button) findViewById(R.id.login_button);

        usersList = Users.getUserList();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPassword(unameText.getText().toString(), passwdText.getText().toString())){
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(menuIntent);
                }
                else {
                    unameText.setText("");
                    passwdText.setText("");
                    Toast.makeText(MainActivity.this, "Login Failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkPassword(String uname, String passwd){
        for(Users user : usersList){
            if(uname.equalsIgnoreCase(user.getUsername()) && passwd.equalsIgnoreCase(user.getPassword())){
                return true;
            }
        }
        return false;
    }

}
