package com.barmej.notesapp.data.database.model;

public class Notes {

    private String noteBodySimple;
    private int backgroundCardNoteColor;


    public Notes(String noteBodySimple, int backgroundCardNoteColor) {
        this.noteBodySimple = noteBodySimple;
        this.backgroundCardNoteColor = backgroundCardNoteColor;
    }


    public String getNoteBodySimple() {
        return noteBodySimple;
    }

    public int getBackgroundCardNoteColor() {
        return backgroundCardNoteColor;
    }

}
