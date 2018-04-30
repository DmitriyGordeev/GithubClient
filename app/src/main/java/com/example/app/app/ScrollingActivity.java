package com.example.app.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;

    public ImageDownloader(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        Bitmap bitmap = null;

        try {
            InputStream input = new URL(urls[0]).openStream();
            bitmap = BitmapFactory.decodeStream(input);
            input.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap image) {
        imageView.setImageBitmap(image);
    }
}




public class ScrollingActivity extends AppCompatActivity {

    private ListView listView_repos;
    private List<Repo> repositories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        View scrollView = (View)findViewById(R.id.scroll_view);
        listView_repos = (ListView)scrollView.findViewById(R.id.listView_repos);

        List<Repo> repos = new ArrayList<>();
        RepoAdapter repoAdapter = new RepoAdapter(ScrollingActivity.this, R.layout.repo_listitem, repos);
        listView_repos.setAdapter(repoAdapter);



        listView_repos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Repo r = (Repo)adapterView.getItemAtPosition(i);

                Intent intent = new Intent(ScrollingActivity.this, CommitsActivity.class);
                intent.putExtra("Repo", r);
                startActivity(intent);
            }
        });



        try {
            getUser();
            getRepositories();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void getRepositories() throws Exception {

        Retrofit.Builder apiBuilder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit apiRetrofit = apiBuilder.build();
        final Client apiClient = apiRetrofit.create(Client.class);


        if(Global.accessToken == null) {
            throw new Exception("accessToken is null");
        }

        Call<List<Repo>> reposCall = apiClient.repos(Global.accessToken);
        Log.i("accessToken", Global.accessToken);
        reposCall.enqueue(new Callback<List<Repo>>() {

            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                if(response == null) {
                    Log.i("REPOS", "response is null");
                    return;
                }
                Log.i("response.message()", response.message() + " code = " + response.code());
                repositories = response.body();

                Log.i("[repositories.size()]", String.valueOf(repositories.size()));
                String repoNames = "";
                for(Repo r : repositories) {
                    repoNames = repoNames.concat(r.getName() + "\n");
                }
                Log.i("[REPOS]", repoNames);



                RepoAdapter repoAdapter = new RepoAdapter(ScrollingActivity.this, R.layout.repo_listitem, repositories);
                listView_repos.setAdapter(repoAdapter);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable throwable) {

            }
        });


    }

    private void getUser() throws Exception {

        // TODO: create single common retrofit object for all connections:

        Retrofit.Builder apiBuilder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit apiRetrofit = apiBuilder.build();
        final Client apiClient = apiRetrofit.create(Client.class);


        if(Global.accessToken == null) {
            throw new Exception("accessToken is null");
        }

        Call<User> reposCall = apiClient.user(Global.accessToken);
        reposCall.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response != null && response.isSuccessful()) {

                    //TODO: check for single user instance & relog
                    Global.user = response.body();
                    Log.i("[User]", Global.user.getName());

                    // todo: setupUser()
                    TextView textView_username = (TextView) findViewById(R.id.textView_username);
                    textView_username.setText(Global.user.getLogin());
                    ImageView imageView_avatar = (ImageView) findViewById(R.id.imageView_avatar);

                    ImageDownloader imageDownloader = new ImageDownloader(imageView_avatar);
                    imageDownloader.execute(Global.user.getAvatarUrl());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {

            }
        });


    }
}
