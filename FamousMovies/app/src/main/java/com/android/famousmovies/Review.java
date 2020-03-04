package com.android.famousmovies;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hakimi on 4/3/2020.
 */
public class Review implements Serializable {

    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("url")
    private Uri url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }
}
