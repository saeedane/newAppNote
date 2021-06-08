package com.barmej.notesapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.barmej.notesapp.data.Model.NotePhoto;
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

    public void insert(NotePhoto note){
        repository.insert(note);
    }

    public void update(NotePhoto note){
        repository.update(note);
    }

    public void delete(NotePhoto note){
        repository.delete(note);
    }

    public void deleteAll(){
        repository.deleteAllNotes();
    }

    public LiveData<NotePhoto> getAllNotes (){
        return allNotePhoto;
    }
}
