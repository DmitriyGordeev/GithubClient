package com.example.app.app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class CommitAdapter extends ArrayAdapter<Commit> {

    private Context context;
    private List<Commit> items;

    public CommitAdapter(Context context, int resource, List<Commit> items) {
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
            v = vi.inflate(R.layout.commit_listitem, parent, false);
        }

        final Commit commit = getItem(position);
        if (commit != null) {

            TextView textView_commitHash = (TextView) v.findViewById(R.id.textView_commitHash);

            if(textView_commitHash != null) {
                String sha = commit.getSha();
                if(sha.length() > 7) {
                    sha = sha.substring(0, 6);
                }

                textView_commitHash.setText(sha);
            }
        }

        return v;
    }

}
