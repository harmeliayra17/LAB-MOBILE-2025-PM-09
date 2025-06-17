package com.example.tpraktikum6_h071231079.data.response;

import com.google.gson.annotations.SerializedName;

public class CharacterOrigin {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
