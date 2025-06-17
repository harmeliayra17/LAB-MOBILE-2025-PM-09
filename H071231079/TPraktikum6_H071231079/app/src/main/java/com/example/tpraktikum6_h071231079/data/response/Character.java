package com.example.tpraktikum6_h071231079.data.response;

import com.google.gson.annotations.SerializedName;

public class Character {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("status")
    private String status;

    @SerializedName("species")
    private String species;

    @SerializedName("type")
    private String type;

    @SerializedName("gender")
    private String gender;

    @SerializedName("origin")
    private CharacterOrigin origin;

    @SerializedName("location")
    private CharacterLocation location;

    @SerializedName("image")
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public CharacterOrigin getOrigin() {
        return origin;
    }

    public CharacterLocation getLocation() {
        return location;
    }

    public String getImage() {
        return image;
    }
}

