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

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String base_url = "https://github.com/";

    // TODO: move from here
    private final String clientId = "35eb7d468f114aa2ad25";
    private final String clientSecret = "f027bc9c9a2d0ba41e148d0f6274322b0500b742";
    private final String authCallback = "testhubclient://callback";

    private final String serviceUri = "https://github.com/login/oauth/authorize";

    private List<Repo> repositories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = getIntent().getData();
        if(uri != null && uri.toString().startsWith(authCallback)) {

            String code = uri.getQueryParameter("code");

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.build();
            final Client client = retrofit.create(Client.class);
            Call<AccessToken> accessTokenCall = client.getAccessToken(clientId, clientSecret, code);


            accessTokenCall.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                    if(response.isSuccessful()) {
                        AccessToken token = response.body();
                        if(token != null) {
                            Global.accessToken = token.getAccessToken();
                            startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
                        }
                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable throwable) {
                    // TODO: show error text or dialog
                }
            });



        }
    }

    public void onLoginClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(serviceUri +
                "?client_id=" + clientId +
                "&scope=repo&redirect_uri=" + authCallback));

        startActivity(intent);
    }



}
