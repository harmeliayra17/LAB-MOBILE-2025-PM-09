package com.example.tp4_H071231079;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Random;

public class Post implements Parcelable {
    private transient User user;
    private String userId;
    private int imageResId;
    private Uri imageUri;
    private String caption;
    private int likes;
    private int comments;
    private transient Random random = new Random();

    // Constructor untuk image resource (drawable) + user
    public Post(User user,int imageResId, String caption) {
        this.user = user;
        this.userId = user != null ? user.getUsername() : "unknown";
        this.imageResId = imageResId;
        this.imageUri = null;
        this.caption = caption;
        generateStats();
    }

    // Constructor untuk image URI (gallery) + user
    public Post(User user, Uri imageUri, String caption) {
        this.user = user;
        this.userId = user != null ? user.getUsername() : "unknown";
        this.imageUri = imageUri;
        this.imageResId = 0;
        this.caption = caption;
        generateStats();
    }

    private void generateStats() {
        likes = random.nextInt(4901) + 100;
        comments = random.nextInt(200);
    }

    protected Post(Parcel in) {
        userId = in.readString();
        imageResId = in.readInt();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
        caption = in.readString();
        likes = in.readInt();
        comments = in.readInt();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeInt(imageResId);
        dest.writeParcelable(imageUri, flags);
        dest.writeString(caption);
        dest.writeInt(likes);
        dest.writeInt(comments);
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public int getImageResId() { return imageResId; }
    public Uri getImageUri() { return imageUri; }
    public String getCaption() { return caption; }
    public int getLikes() { return likes; }
    public int getComments() { return comments; }
    public boolean hasImageUri() { return imageUri != null; }

    @Override
    public int describeContents() { return 0; }
}