package com.example.app.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    private ListView listView_repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        View scrollView = (View)findViewById(R.id.scroll_view);
        listView_repos = (ListView)scrollView.findViewById(R.id.listView_repos);

        ArrayList<Repo> repos = new ArrayList<>();
        repos.add(new Repo("Repo 1"));
        repos.add(new Repo("Repo 2"));
        repos.add(new Repo("Repo 3"));
        repos.add(new Repo("Repo 1"));
        repos.add(new Repo("Repo 2"));
        repos.add(new Repo("Repo 3"));
        repos.add(new Repo("Repo 1"));
        repos.add(new Repo("Repo 2"));
        repos.add(new Repo("Repo 3"));
        repos.add(new Repo("Repo 1"));
        repos.add(new Repo("Repo 2"));
        repos.add(new Repo("Repo 3"));


        RepoAdapter repoAdapter = new RepoAdapter(this, R.layout.repo_listitem, repos);
        listView_repos.setAdapter(repoAdapter);

    }
}
