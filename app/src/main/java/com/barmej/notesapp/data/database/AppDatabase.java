package com.barmej.notesapp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.barmej.notesapp.data.database.converters.UriConverters;
import com.barmej.notesapp.data.database.dao.CheckNoteDao;
import com.barmej.notesapp.data.database.dao.NotePhotoDao;
import com.barmej.notesapp.data.database.dao.NotesDao;
import com.barmej.notesapp.data.model.CheckNote;
import com.barmej.notesapp.data.model.NotePhoto;
import com.barmej.notesapp.data.model.Notes;

@Database(entities = {NotePhoto.class, CheckNote.class, Notes.class}, version = 3, exportSchema = false)
@TypeConverters({UriConverters.class})
public abstract class AppDatabase  extends RoomDatabase {


    /**
     * Instance of this class for Singleton
     */
    private static final Object LOCK = new Object();

    /**
     * Database file name
     */
    private static final String DATABASE_NAME = "weather_db";

    /**
     * Instance of this class for Singleton
     */
    private static AppDatabase sInstance;

    /**
     * Method used to get an instance of AppDatabase class
     *
     * @param context Context to use for Room initializations
     * @return an instance of AppDatabase class
     */
    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) sInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME
                ).fallbackToDestructiveMigration().build();
            }
        }
        return sInstance;
    }


    /**
     * Return object of ForecastDao to read, write, delete and update forecasts info
     * @return an instance of weatherInfoDao
     */
    public abstract NotePhotoDao notePhotoDao();
    public abstract CheckNoteDao checkNoteDao();
    public abstract NotesDao notesDao();



}
