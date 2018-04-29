package com.example.app.app;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class RepoAdapter extends ArrayAdapter<Repo> {

    Context context;
    List<Repo> repositories;

    public RepoAdapter(Context context, int resource, List<Repo> items) {
        super(context, resource, items);
        this.context = context;
        repositories = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.repo_listitem, null);
        }

        final Repo r = getItem(position);
        if (r != null) {

            TextView textView_repoName = (TextView) v.findViewById(R.id.textView_repoName);
            TextView textView_repoCommits = (TextView) v.findViewById(R.id.textView_repoCommits);
            ImageView imageView_authorAvatar = (ImageView) v.findViewById(R.id.imageView_authorAvatar);


            if(textView_repoName != null) {
                textView_repoName.setText(r.getName());
            }

//            if(textView_repoCommits != null) {
//                textView_repoCommits.setText(m.getYear());
//            }

//            if(btn_addToFavorites != null) {
//                btn_addToFavorites.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View view) {
//                        if(rmMode) {
//                            movies.remove(position);
//                            notifyDataSetChanged();
//                            Toast.makeText(context, "Удалено из избранного", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//                            Toast.makeText(context, "Добавлено в избранное", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }

        }

        return v;
    }

}
