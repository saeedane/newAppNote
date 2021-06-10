package com.barmej.notesapp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.barmej.notesapp.data.database.model.CheckNote;

@Dao
public interface CheckNoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CheckNote checkNote);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(CheckNote checkNote);

    @Delete
    void delete(CheckNote checkNote);

    @Query(" DELETE FROM check_note_table")
    void deleteAll ();

    @Query("SELECT * FROM check_note_table  ")
    LiveData<CheckNote> getAllNoteCheck();

}
