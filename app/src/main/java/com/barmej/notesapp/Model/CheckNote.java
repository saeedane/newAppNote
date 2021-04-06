package com.barmej.notesapp.Model;

public class CheckNote {

    private String noteBody;
    private boolean isChecked;

    public CheckNote(String noteBody, boolean isChecked) {
        this.noteBody = noteBody;
        this.isChecked = isChecked;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
