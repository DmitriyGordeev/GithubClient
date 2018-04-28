package com.example.app.app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String username = "dmitriygordeev";
    private final String base_url = "https://api.github.com/";

    // TODO: move from here
    private final String clientId = "35eb7d468f114aa2ad25";
    private final String clientSecret = "f027bc9c9a2d0ba41e148d0f6274322b0500b742";
    private final String authCallback = "testhubclient://callback";

    private final String serviceUri = "https://github.com/login/oauth/authorize";

    private EditText editText_userEmail;
    private EditText editText_userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_userEmail = (EditText) findViewById(R.id.editText_userEmail);
        editText_userPassword = (EditText) findViewById(R.id.editText_userPassword);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(serviceUri +
                "?client_id=" + clientId +
                "&scope=repo&redirect_uri=" + authCallback));

        startActivity(intent);
    }

    public void onLoginClick(View view) {

    }
}
