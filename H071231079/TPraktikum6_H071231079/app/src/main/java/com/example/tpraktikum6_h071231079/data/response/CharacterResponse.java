package com.example.tpraktikum6_h071231079.data.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CharacterResponse {

    @SerializedName("info")
    private Info info;

    @SerializedName("results")
    private List<Character> results;

    public Info getInfo() {
        return info;
    }

    public List<Character> getResults() {
        return results;
    }
}
