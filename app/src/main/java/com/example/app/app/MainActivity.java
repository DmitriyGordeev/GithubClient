package com.example.app.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText_userEmail;
    private EditText editText_userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_userEmail = (EditText) findViewById(R.id.editText_userEmail);
        editText_userPassword = (EditText) findViewById(R.id.editText_userPassword);
    }

    public void onLoginClick(View view) {
        Log.i("onLoginClick()", "clicking");
    }
}
