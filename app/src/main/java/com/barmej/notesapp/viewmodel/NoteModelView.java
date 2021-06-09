package com.barmej.notesapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.barmej.notesapp.data.database.model.NotePhoto;
import com.barmej.notesapp.data.NoteDataRepository;

import java.util.List;

public class NoteModelView extends AndroidViewModel {
    private NoteDataRepository repository;
    private LiveData<NotePhoto> allNotePhoto;
    public NoteModelView(@NonNull Application application) {
        super(application);
        repository = NoteDataRepository.getInstance(application);
        allNotePhoto = repository.getNotePhotoInfo();
    }

    public void insert(NotePhoto notePhoto){
        repository.insert(notePhoto);
    }

    public void update(NotePhoto notePhoto){
        repository.update(notePhoto);
    }

    public void delete(NotePhoto notePhoto){
        repository.delete(notePhoto);
    }

    public void deleteAll(){
        repository.deleteAllNotes();
    }

    public LiveData<NotePhoto> getAllNotes (){

        return allNotePhoto;
    }
}
