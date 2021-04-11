package com.barmej.notesapp.Model;

public class CheckNote {

    private String noteBodyCheck;
    public static int backgroundCardNoteColor;


    public CheckNote(String noteBodyCheck, int backgroundCardNoteColor) {
        this.noteBodyCheck = noteBodyCheck;
        this.backgroundCardNoteColor = backgroundCardNoteColor;
    }

    public String getNoteBodyCheck() {
        return noteBodyCheck;
    }

    public int getBackgroundCardNoteColor() {
        return backgroundCardNoteColor;
    }


}
