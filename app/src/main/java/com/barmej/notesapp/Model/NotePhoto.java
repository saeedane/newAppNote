package com.barmej.notesapp.Model;

import android.net.Uri;

public class NotePhoto {
    private String noteBody;
    private Uri noteImage;

    public NotePhoto(String noteBody, Uri noteImage) {
        this.noteBody = noteBody;
        this.noteImage = noteImage;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public Uri getNoteImage() {
        return noteImage;
    }
}
