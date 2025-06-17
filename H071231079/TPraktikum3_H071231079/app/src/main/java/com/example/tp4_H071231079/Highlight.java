package com.example.tp4_H071231079;

import android.os.Parcel;
import android.os.Parcelable;

public class Highlight implements Parcelable {

    private int imageResId;
    private String title;

    public Highlight(int imageResId, String title) {
        this.imageResId = imageResId;
        this.title = title;
    }

    // Getter methods
    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    protected Highlight(Parcel in) {
        imageResId = in.readInt();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResId);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // CREATOR
    public static final Creator<Highlight> CREATOR = new Creator<Highlight>() {
        @Override
        public Highlight createFromParcel(Parcel in) {
            return new Highlight(in);
        }

        @Override
        public Highlight[] newArray(int size) {
            return new Highlight[size];
        }
    };
}
