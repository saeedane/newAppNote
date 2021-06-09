package com.barmej.notesapp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.barmej.notesapp.data.database.model.CheckNote;
import com.barmej.notesapp.data.database.model.NotePhoto;

public interface CheckNoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CheckNote checkNote);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(CheckNote checkNote);

    @Delete
    void delete(CheckNote checkNote);

    @Query(" DELETE FROM note_table_photo")
    void deleteAll ();

    @Query("SELECT * FROM note_table_photo  ")
    LiveData<CheckNote> getAllNoteCheck();

}
