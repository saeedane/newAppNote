package com.barmej.notesapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.barmej.notesapp.data.database.model.CheckNote;
import com.barmej.notesapp.data.database.model.NotePhoto;
import com.barmej.notesapp.data.NoteDataRepository;

import java.util.List;

public class NoteModelView extends AndroidViewModel {
    private NoteDataRepository repository;
    private LiveData<NotePhoto> allNotePhoto;
    private LiveData<CheckNote> allNoteCheck;
    public NoteModelView(@NonNull Application application) {
        super(application);
        repository = NoteDataRepository.getInstance(application);
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
     * repository get note checked
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

}
