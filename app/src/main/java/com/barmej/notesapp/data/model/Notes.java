package com.barmej.notesapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "simple_note_table")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "description_simple_note")
    private String description;
    @ColumnInfo(name = "color_simple_note")
    private int color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Notes(String description, int color) {
        this.description = description;
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
