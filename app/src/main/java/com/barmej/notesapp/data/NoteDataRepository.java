package com.barmej.notesapp.data;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import androidx.lifecycle.LiveData;

import com.barmej.notesapp.data.database.dao.CheckNoteDao;
import com.barmej.notesapp.data.database.dao.NotesDao;
import com.barmej.notesapp.data.model.CheckNote;
import com.barmej.notesapp.data.database.AppDatabase;
import com.barmej.notesapp.data.database.dao.NotePhotoDao;
import com.barmej.notesapp.data.model.NotePhoto;
import com.barmej.notesapp.data.model.Notes;

public class NoteDataRepository {

    private static final String TAG = NoteDataRepository.class.getSimpleName();

    /**
     * Object used for the purpose of synchronize lock
     */
    private static final Object LOCK = new Object();

    /**
     * Instance of this class for Singleton
     */
    private static NoteDataRepository sInstance;


    /**
     * Instance of AppDatabase to perform database operation
     */
    private AppDatabase mAppDatabase;
    private AppExecutor appExecutor;

/**
 * Instance of AppExecutor to perform tasks on worker threads
}
 */
private NotePhotoDao notePhotoDao;
private CheckNoteDao checkNoteDao;
private NotesDao notesDao;



 /**
 * Method used to get an instance of WeatherDataRepository class
 *
 * @param context Context to use for some initializations
 * @return an instance of WeatherDataRepository class
 */
public static NoteDataRepository getInstance(Context context) {
    if (sInstance == null) {
        synchronized (LOCK) {
            if (sInstance == null)
                sInstance = new NoteDataRepository(context.getApplicationContext());
        }
    }
    return sInstance;
}

    /**
     * @param context Context to use for some initializations
     */
    @TargetApi(Build.VERSION_CODES.N)
    private NoteDataRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        notesDao = mAppDatabase.notesDao();
        notePhotoDao = mAppDatabase.notePhotoDao();
        checkNoteDao = mAppDatabase.checkNoteDao();
        appExecutor = AppExecutor.getInstance();

    }


    // Room doesn't allow database operations run in the Main thread note photo


    public void insertNotePhoto(final NotePhoto note){
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.notePhotoDao().insert(note);
            }
        });
    }

    public void deleteNotePhoto(final NotePhoto note){
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.notePhotoDao().delete(note);
            }
        });

    }

    public void updateNotePhoto(final NotePhoto note){
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.notePhotoDao().update(note);
            }
        });    }

    public void deleteAllNotePhoto() {
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.notePhotoDao().deleteAll();
            }
        });

    }



    /**
     * app executor check notes
     * */

    public void insertNoteCheck(final CheckNote note){
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.checkNoteDao().insert(note);
            }
        });
    }

    public void deleteNoteCheck(final CheckNote note){
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.checkNoteDao().delete(note);
            }
        });

    }

    public void updateNoteCheck(final CheckNote note){
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.checkNoteDao().update(note);
            }
        });    }

    public void deleteAllNoteCheck() {
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.checkNoteDao().deleteAll();
            }
        });

    }


    /**
     * app executor check notes
     * */

    public void insertSimpleNotes(final Notes note){
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.notesDao().insert(note);
            }
        });
    }

    public void deleteSimpleNotes(final Notes note){
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.notesDao().delete(note);
            }
        });

    }

    public void updateSimpleNotes(final Notes note){
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.notesDao().update(note);
            }
        });    }

    public void deleteAllSimpleNotes() {
        appExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.notesDao().deleteAll();
            }
        });

    }


    /**
     * Get all note photo and all note check  data and simple note
     *
     */


    public LiveData<NotePhoto> getNotePhotoInfo() {
        // Get LiveData object from database using Room
        return mAppDatabase.notePhotoDao().getAllNotePhotos();
    }



    public LiveData<CheckNote> getCheckedNoteInfo() {
        // Get LiveData object from database using Room
        return mAppDatabase.checkNoteDao().getAllNoteCheck();
    }

    public LiveData<Notes> getSimpleNoteInfo() {
        // Get LiveData object from database using Room
        return mAppDatabase.notesDao().getAllSimpleNotes();
    }




}