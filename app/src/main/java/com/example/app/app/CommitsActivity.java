package com.example.app.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class CommitsActivity extends AppCompatActivity {

    ListView listView_commits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);

        Intent intent = getIntent();
        Repo r = (Repo)intent.getParcelableExtra("Repo");

        listView_commits = (ListView) findViewById(R.id.listView_commits);

    }
}
