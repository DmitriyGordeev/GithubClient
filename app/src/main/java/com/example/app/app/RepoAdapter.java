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


public class MovieAdapter extends ArrayAdapter<Repo> {

    Context context;
    boolean rmMode;
    List<Repo> movies;

    public MovieAdapter(Context context, int resource, List<Repo> items, boolean rmMode) {
        super(context, resource, items);
        this.context = context;

        this.rmMode = rmMode;
        movies = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listitem_movie, null);
        }

        final Repo m = getItem(position);
        if (m != null) {

            TextView tv_movieTitle = (TextView) v.findViewById(R.id.tv_movieTitle);
            TextView tv_movieYear = (TextView) v.findViewById(R.id.tv_movieYear);
            ImageView iv_moviePoster = (ImageView) v.findViewById(R.id.iv_moviePoster);
            ImageButton btn_addToFavorites = (ImageButton) v.findViewById(R.id.btn_addToFavorites);

            if(rmMode) {
                btn_addToFavorites.setImageResource(android.R.drawable.ic_menu_delete);
            }

            if(tv_movieTitle != null) {
                tv_movieTitle.setText(m.getTitle());
            }

            if(tv_movieYear != null) {
                tv_movieYear.setText(m.getYear());
            }

            if(btn_addToFavorites != null) {
                btn_addToFavorites.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if(rmMode) {
                            movies.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Удалено из избранного", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Добавлено в избранное", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }

        return v;
    }

}
