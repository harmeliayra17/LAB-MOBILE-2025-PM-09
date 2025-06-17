package com.example.tpraktikum4_h071231079;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int id;
    private String title;
    private String author;
    private int year;
    private String description;
    private String genre;
    private float rating;
    private String[] reviews;
    private String coverUri;
    private int coverImg;
    private boolean isFavorite;

    // Constructor untuk dummy data (dari drawable)
    public Book(int id, String title, String author, int year, String description,
                String genre, float rating, String[] reviews, int coverImg) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.reviews = reviews;
        this.coverImg = coverImg;
        this.coverUri = null;
        this.isFavorite = false;
    }

    // Constructor untuk data dari user (gallery)
    public Book(int id, String title, String author, int year, String description,
                String genre, float rating, String[] reviews, String coverUri) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.reviews = reviews;
        this.coverImg = 0;
        this.coverUri = coverUri;
        this.isFavorite = false;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        year = in.readInt();
        description = in.readString();
        genre = in.readString();
        rating = in.readFloat();
        reviews = in.createStringArray();
        coverUri = in.readString();
        coverImg = in.readInt();
        isFavorite = in.readByte() != 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }
        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeInt(year);
        dest.writeString(description);
        dest.writeString(genre);
        dest.writeFloat(rating);
        dest.writeStringArray(reviews);
        dest.writeString(coverUri);
        dest.writeInt(coverImg);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public String[] getReviews() {
        return reviews;
    }
    public void setReviews(String[] reviews) {
        this.reviews = reviews;
    }
    public String getCoverUri() {
        return coverUri;
    }
    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }
    public int getCoverImg() {
        return coverImg;
    }
    public void setCoverImg(int coverImg) {
        this.coverImg = coverImg;
    }
    public boolean isFavorite() {
        return isFavorite;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}