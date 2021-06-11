package com.barmej.notesapp.data.model;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table_photo")
public class NotePhoto {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "description_note_photo")
    private String description;
    @ColumnInfo(name = "image_note_photo")
    private Uri image;
    @ColumnInfo(name = "color_note_photo")
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
