package com.example.app.app;

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

    private EditText editText_userEmail;
    private EditText editText_userPassword;

    private final String username = "dmitriygordeev";
    private final String base_url = "https://api.github.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_userEmail = (EditText) findViewById(R.id.editText_userEmail);
        editText_userPassword = (EditText) findViewById(R.id.editText_userPassword);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        Client client = retrofit.create(Client.class);
        Call<List<Repo>> call = client.repos(username);

        Log.i("[logging]", "Here...");

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.i("[logging]", "onResponse()");

                List<Repo> repos = response.body();
                String repoNames = "";
                for(Repo r : repos) {
                    repoNames = repoNames.concat(r.getName() + "\n");
                }
                Log.i("[REPOS]", repoNames);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "connection failure!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onLoginClick(View view) {

    }
}
