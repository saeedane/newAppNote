package com.barmej.notesapp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.barmej.notesapp.data.model.NotePhoto;

@Dao
public interface NotePhotoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(NotePhoto notePhoto);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(NotePhoto notePhoto);

    @Delete
    void delete(NotePhoto notePhoto);

    @Query(" DELETE FROM note_table_photo")
    void deleteAll ();

    @Query("SELECT * FROM note_table_photo  ")
    LiveData<NotePhoto> getAllNotePhotos();
}
