package com.example.labpertemuan8;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Notes implements Parcelable {

    private String title;

    private String description;

    private int id;

    private String createdAt;
    private String updatedAt;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String Title) {
        this.title = title;
    }

    public void setDescription(String Description) {
        this.description = description;
    }


    public Notes(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public Notes(int id, String title, String description, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Notes() {
    }


    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
