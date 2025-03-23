package com.example.learningprogramminglanguages.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "complete_lessons")
public class Lesson implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("language")
    private String language;
    @SerializedName("title")
    private String title;
    @Ignore
    @SerializedName("text")
    private String text;
    @Ignore
    @SerializedName("isLock")
    private boolean isLock;

    public Lesson(int id, String language, String title) {
        this.id = id;
        this.language = language;
        this.title = title;
    }

    @Ignore
    public Lesson(int id, String language, String title, String text, Boolean isLock) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.isLock = isLock;
    }

    public int getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", isLock=" + isLock +
                '}';
    }
}
