package com.barmej.notesapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.barmej.notesapp.data.model.CheckNote;
import com.barmej.notesapp.data.model.NotePhoto;
import com.barmej.notesapp.data.NoteDataRepository;
import com.barmej.notesapp.data.model.Notes;

public class NoteModelView extends AndroidViewModel {
    private NoteDataRepository repository;
    private LiveData<NotePhoto> allNotePhoto;
    private LiveData<CheckNote> allNoteCheck;
    private LiveData<Notes> allSimpleNote;
    public NoteModelView(@NonNull Application application) {
        super(application);
        repository = NoteDataRepository.getInstance(application);
        allSimpleNote = repository.getSimpleNoteInfo();
        allNotePhoto = repository.getNotePhotoInfo();
        allNoteCheck = repository.getCheckedNoteInfo();
    }

    public void insertNotePhoto(NotePhoto notePhoto){
        repository.insertNotePhoto(notePhoto);
    }

    public void updateNotePhoto(NotePhoto notePhoto){
        repository.updateNotePhoto(notePhoto);
    }

    public void deleteNotePhoto(NotePhoto notePhoto){
        repository.deleteNotePhoto(notePhoto);
    }

    public void deleteAllNotePhoto(){
        repository.deleteAllNotePhoto();
    }

    public LiveData<NotePhoto> getAllNotePhoto(){

        return allNotePhoto;
    }


    /**
     *  get note checked repository
     */


    public void insertNoteCheck(CheckNote checkNote){
        repository.insertNoteCheck(checkNote);
    }

    public void updateNoteCheck(CheckNote checkNote){
        repository.updateNoteCheck(checkNote);
    }

    public void deleteNoteCheck(CheckNote checkNote){
        repository.deleteNoteCheck(checkNote);
    }

    public void deleteAllNoteCheck(){
        repository.deleteAllNotePhoto();
    }

    public LiveData<CheckNote> getAllNoteCheck(){

        return allNoteCheck;
    }




    /**
     *  get note checked repository
     */
    public void insertSimpleNotes(Notes notes){
        repository.insertSimpleNotes(notes);
    }

    public void updateSimpleNotes(Notes notes){
        repository.updateSimpleNotes(notes);
    }

    public void deleteSimpleNotes(Notes notes){
        repository.deleteSimpleNotes(notes);
    }

    public void deleteAllSimpleNotes(){
        repository.deleteAllSimpleNotes();
    }

    public LiveData<Notes> getAllSimpleNote(){

        return allSimpleNote;
    }


}
