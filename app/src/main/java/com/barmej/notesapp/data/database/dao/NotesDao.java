package com.barmej.notesapp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.barmej.notesapp.data.model.CheckNote;
import com.barmej.notesapp.data.model.Notes;

@Dao
public interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Notes notes);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Notes notes);

    @Delete
    void delete(Notes notes);

    @Query(" DELETE FROM simple_note_table")
    void deleteAll();

    @Query("SELECT * FROM simple_note_table  ")
    LiveData<Notes> getAllSimpleNotes();

}
