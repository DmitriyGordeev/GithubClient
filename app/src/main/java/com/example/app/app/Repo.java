package com.example.app.app;

import android.os.Parcel;
import android.os.Parcelable;

public class Repo implements Parcelable {

    public Repo(String name) {
        this.name = name;
    }

    private String name;
    public String getName() {return name;}

    protected Repo(Parcel in) {
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
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
