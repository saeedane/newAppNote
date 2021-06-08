package com.barmej.notesapp.data.Model;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="note_table_photo")
public class NotePhoto {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description ;
    private Uri image;
    private int color;

    public NotePhoto(String description, Uri image, int color) {
        this.description = description;
        this.image = image;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
