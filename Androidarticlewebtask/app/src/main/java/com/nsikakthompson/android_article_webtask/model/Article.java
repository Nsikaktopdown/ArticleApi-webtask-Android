package com.nsikakthompson.android_article_webtask.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nsikak on 2/21/18.
 */

public class Article {

    @SerializedName("author")
    String author;
    @SerializedName("content")
    String  content;
    @SerializedName("title")
    String title;
    @SerializedName("id")
    String id;
    @SerializedName("imageurl")
    String imageurl;

    public Article(String author, String content, String title, String id, String imageurl) {
        this.author = author;
        this.content = content;
        this.title = title;
        this.id = id;
        this.imageurl = imageurl;
    }

    public Article() {
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
