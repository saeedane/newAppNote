package com.barmej.notesapp.data.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "check_note_table")
public class CheckNote {

    @PrimaryKey
    private int id;
    private String title ;
    private int color;
    private boolean is_checkd = false;

    public CheckNote(String title, int color, boolean is_checkd) {
        this.title = title;
        this.color = color;
        this.is_checkd = is_checkd;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isCheked() {
        return is_checkd;
    }

    public void setCheked(boolean is_checkd) {
        this.is_checkd = is_checkd;
    }
}
