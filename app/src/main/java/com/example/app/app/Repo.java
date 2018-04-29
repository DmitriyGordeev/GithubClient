package com.example.app.app;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Repo implements Parcelable {

    private String name;

    @SerializedName("commits_url")
    private String commitsUrl;

    public Repo(String name, String commitsUrl) {
        this.name = name;
        this.commitsUrl = commitsUrl;
    }

    public String getName() { return this.name; }
    public String getCommitsUrl() { return this.commitsUrl; }


    protected Repo(Parcel in) {
        name = in.readString();
        commitsUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(commitsUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Repo> CREATOR = new Parcelable.Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel in) {
            return new Repo(in);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };
}
