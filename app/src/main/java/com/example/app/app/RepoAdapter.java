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

    private Context context;
    private List<Repo> items;

    public RepoAdapter(Context context, int resource, List<Repo> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.repo_listitem, parent, false);
        }

        final Repo r = getItem(position);
        if (r != null) {

            TextView textView_repoName = (TextView) v.findViewById(R.id.textView_repoName);
            TextView textView_private= (TextView) v.findViewById(R.id.textView_description);
            ImageView imageView_authorAvatar = (ImageView) v.findViewById(R.id.imageView_authorAvatar);

            if(textView_repoName != null) {
                textView_repoName.setText(r.getName());
            }

            if(textView_private != null) {
                textView_private.setText(r.isPrivate() ? "private repository" : "public repository");
            }
        }

        return v;
    }

}
