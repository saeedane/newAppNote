package com.barmej.notesapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "check_note_table")

public class CheckNote {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    @ColumnInfo(name = "description_note_check")
    private String description ;
    @ColumnInfo(name = "color_note_check")
    private int color;
    @ColumnInfo(name = "option_checked")
    private boolean checked ;

    public CheckNote(String description, int color, boolean checked) {
        this.description = description;
        this.color = color;
        this.checked = checked;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
