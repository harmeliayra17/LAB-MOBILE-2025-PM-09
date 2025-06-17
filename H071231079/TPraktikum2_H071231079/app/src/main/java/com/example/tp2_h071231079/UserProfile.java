package com.example.tp2_h071231079;

import android.os.Parcel;
import android.os.Parcelable;

public class UserProfile implements Parcelable {
    private String name;
    private String username;
    private String bio;
    private String gender;
    private String birthDate;
    private String profilePicture;

    // Constructor lengkap
    public UserProfile(String name, String username, String bio, String gender, String birthDate, String profilePicture) {
        this.name = name;
        this.username = username;
        this.bio = bio;
        this.gender = gender;
        this.birthDate = birthDate;
        this.profilePicture = profilePicture;
    }

    // Constructor kosong
    public UserProfile() {}

    // Copy constructor
    public UserProfile(UserProfile other) {
        this.name = other.name;
        this.username = other.username;
        this.bio = other.bio;
        this.gender = other.gender;
        this.birthDate = other.birthDate;
        this.profilePicture = other.profilePicture;
    }

    // Getter dan Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    // Parcelable implementation
    protected UserProfile(Parcel in) {
        name = in.readString();
        username = in.readString();
        bio = in.readString();
        gender = in.readString();
        birthDate = in.readString();
        profilePicture = in.readString();
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(bio);
        parcel.writeString(gender);
        parcel.writeString(birthDate);
        parcel.writeString(profilePicture);
    }

    // equals() override agar perbandingan benar
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserProfile)) return false;
        UserProfile that = (UserProfile) obj;

        return name.equals(that.name) &&
                username.equals(that.username) &&
                bio.equals(that.bio) &&
                gender.equals(that.gender) &&
                birthDate.equals(that.birthDate) &&
                ((profilePicture == null && that.profilePicture == null) ||
                        (profilePicture != null && profilePicture.equals(that.profilePicture)));
    }
}
