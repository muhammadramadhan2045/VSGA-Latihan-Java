package com.example.mypens.model;

import androidx.annotation.NonNull;

public class Feed {
    private int id;
    private String title;
    private String subtitle;


    public Feed(int id, String title, String subtitle) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @NonNull
    @Override
    public String toString() {
        return "Feed{" +
                "id=" + id + '\'' +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }
}
