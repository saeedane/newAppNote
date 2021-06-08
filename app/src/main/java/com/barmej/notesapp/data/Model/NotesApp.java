package com.barmej.notesapp.data.Model;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName ="notes_table")
public class NotesApp {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description ;
    private Uri image;
    private int color;
    private boolean isChecked = false;

    public NotesApp(String description, Uri image, int color) {
        this.description = description;
        this.image = image;
        this.color = color;
    }

    public NotesApp(String description, int color, boolean isChecked) {

        this.description = description;
        this.color = color;
        this.isChecked = isChecked;
    }

    public NotesApp(String description, int color) {
        this.description = description;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
