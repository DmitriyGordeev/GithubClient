package com.example.app.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;

public class CommitsActivity extends AppCompatActivity {

    ListView listView_commits;
    Repo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);

        Intent intent = getIntent();
        repo = (Repo)intent.getParcelableExtra("Repo");
        if(repo == null) {
            //!!!
        }

        listView_commits = (ListView) findViewById(R.id.listView_commits);
        CommitAdapter commitAdapter = new CommitAdapter(this,
                R.layout.commit_listitem,
                new ArrayList<Commit>());
        listView_commits.setAdapter(commitAdapter);


        try {
            getCommits();
        }
        catch(Exception e) {
            e.printStackTrace();
        }


    }

    private void getCommits() throws Exception {

        Retrofit.Builder apiBuilder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = apiBuilder.build();
        final Client apiClient = retrofit.create(Client.class);


        if(Global.accessToken == null) {
            throw new Exception("Global.accessToken is null");
        }

        if(Global.user == null) {
            throw new Exception("Global.user is null");
        }

        Call<List<Commit>> commitsCall
                = apiClient.commits(Global.user.getLogin(), repo.getName(), Global.accessToken);

        commitsCall.enqueue(new Callback<List<Commit>>() {
            @Override
            public void onResponse(Call<List<Commit>> call, Response<List<Commit>> response) {

                if(response == null) {
                    return;
                }
                List<Commit> commits = response.body();
                CommitAdapter commitAdapter = new CommitAdapter(CommitsActivity.this,
                        R.layout.commit_listitem,
                        commits);
                listView_commits.setAdapter(commitAdapter);
            }

            @Override
            public void onFailure(Call<List<Commit>> call, Throwable throwable) {

                // TODO: check is failure
            }
        });
    }
}
