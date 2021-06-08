package com.barmej.notesapp.data.Model;

public class CheckNote {

    private String noteBodyCheck ;
    private int backgroundCardNoteColor;
    private boolean isChecked = false;


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

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
