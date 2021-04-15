package com.barmej.notesapp.Model;

public class CheckNote {

    private String noteBodyCheck;
    public  int backgroundCardNoteColor;
    public  boolean isChecked ;


    public CheckNote(String noteBodyCheck, int backgroundCardNoteColor) {
        this.noteBodyCheck = noteBodyCheck;
        this.backgroundCardNoteColor = backgroundCardNoteColor;
    }

    public CheckNote(String noteBodyCheck, int backgroundCardNoteColor, boolean isChecked) {
        this.noteBodyCheck = noteBodyCheck;
        this.backgroundCardNoteColor = backgroundCardNoteColor;
        this.isChecked = isChecked;
    }

    public String getNoteBodyCheck() {
        return noteBodyCheck;
    }

    public int getBackgroundCardNoteColor() {
        return backgroundCardNoteColor;
    }


    public boolean isChecked() {
        return isChecked;
    }
}
