package com.example.tp4_H071231079;

import android.os.Parcel;
import android.os.Parcelable;

public class Story implements Parcelable {
    private int imageRes;

    public Story(int imageRes) {
        this.imageRes = imageRes;
    }

    // Getter
    public int getImageRes() {
        return imageRes;
    }

    // Parcelable implementation
    protected Story(Parcel in) {
        imageRes = in.readInt();
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageRes);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
