package com.example.app.app;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Repo implements Parcelable {

    private String name;

    @SerializedName("commits_url")
    private String commitsUrl;

    @SerializedName("private")
    private boolean is_private;

    public Repo(String name, boolean is_private, String commitsUrl) {
        this.name = name;
        this.is_private = is_private;
        this.commitsUrl = commitsUrl;
    }

    public String getName() { return this.name; }
    public String getCommitsUrl() { return this.commitsUrl; }
    public boolean isPrivate() { return this.is_private; }


    protected Repo(Parcel in) {
        name = in.readString();
        is_private = in.readByte() != 0x00;
        commitsUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (is_private ? 0x01 : 0x00));
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
