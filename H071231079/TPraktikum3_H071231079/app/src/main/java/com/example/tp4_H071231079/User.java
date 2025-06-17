package com.example.tp4_H071231079;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {
    private int profileImage;
    private String username;
    private String name;
    private String bio;
    private int postsCount;
    private int followersCount;
    private int followingCount;
    private String followedBy;
    private List<Highlight> highlights;
    private List<Post> posts;
    private Story story;

    // Constructor
    public User(int profileImage, String username, String name, String bio,
                int followersCount, int followingCount,
                String followedBy, int[] highlightImages,
                String[] highlightTitles, Story story, List<Post> posts) {
        this.profileImage = profileImage;
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.followedBy = followedBy;

        // Inisialisasi highlights
        this.highlights = new ArrayList<>();
        if (highlightImages != null && highlightTitles != null &&
                highlightImages.length == highlightTitles.length) {
            for (int j = 0; j < highlightImages.length; j++) {
                highlights.add(new Highlight(highlightImages[j], highlightTitles[j]));
            }
        }

        // Mengatur posts secara otomatis dengan likes dan comments
        this.posts = (posts != null) ? posts : new ArrayList<>();

        this.postsCount = this.posts.size();

        this.story = story;
    }

    public Uri getProfileImageUri() {
        // Jika profile disimpan sebagai URI
        return Uri.parse("content://media/" + this.profileImage);
    }

    // Konstruktor Parcel
    protected User(Parcel in) {
        profileImage = in.readInt();
        username = in.readString();
        name = in.readString();
        bio = in.readString();
        postsCount = in.readInt();
        followersCount = in.readInt();
        followingCount = in.readInt();
        followedBy = in.readString();
        highlights = in.createTypedArrayList(Highlight.CREATOR);
        story = in.readParcelable(Story.class.getClassLoader());
    }

    // Parcelable CREATOR
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(profileImage);
        parcel.writeString(username);
        parcel.writeString(name);
        parcel.writeString(bio);
        parcel.writeInt(postsCount);
        parcel.writeInt(followersCount);
        parcel.writeInt(followingCount);
        parcel.writeString(followedBy);
        parcel.writeTypedList(highlights);
        parcel.writeParcelable(story, flags);
    }

    // Getter
    public int getProfileImage() { return profileImage; }
    public String getUsername() { return username; }
    public String getName() { return name; }
    public String getBio() { return bio; }
    public int getPostsCount() { return postsCount; }
    public int getFollowersCount() { return followersCount; }
    public int getFollowingCount() { return followingCount; }
    public String getFollowedBy() { return followedBy; }
    public List<Highlight> getHighlights() { return highlights; }
    public List<Post> getPosts() { return posts; }
    public Story getStory() { return story; }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    // Setter
    public void setPostCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        this.postsCount = posts.size();
    }

    public void addPost(Post post) {
        if (this.posts == null) {
            this.posts = new ArrayList<>();
        }
        this.posts.add(post);
    }
}
