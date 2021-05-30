package com.barmej.notesapp.Model;

import android.net.Uri;

public class NotePhoto {
    private String noteBodyPhoto ;
    private Uri noteImage;
    private int backgroundCardNoteColor;


    public NotePhoto(String noteBodyPhoto, Uri noteImage, int backgroundCardNoteColor) {
        this.noteBodyPhoto = noteBodyPhoto;
        this.noteImage = noteImage;
        this.backgroundCardNoteColor = backgroundCardNoteColor;
    }


    public String getNoteBodyPhoto() {
        return noteBodyPhoto;
    }

    public Uri getNoteImage() {
        return noteImage;
    }

    public int getBackgroundCardNoteColor() {
        return backgroundCardNoteColor;
    }
}
