package com.barmej.notesapp.data;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import androidx.lifecycle.LiveData;

import com.barmej.notesapp.data.database.dao.CheckNoteDao;
import com.barmej.notesapp.data.database.model.CheckNote;
import com.barmej.notesapp.data.database.model.NotePhoto;
import com.barmej.notesapp.data.database.AppDatabase;
import com.barmej.notesapp.data.database.dao.NotePhotoDao;

import java.util.List;

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

/**
 * Instance of AppExecutor to perform tasks on worker threads
}
 */
private NotePhotoDao notePhotoDao;
private CheckNoteDao checkNoteDao;


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
        notePhotoDao = mAppDatabase.notePhotoDao();
        checkNoteDao = mAppDatabase.checkNoteDao();

    }


    // Room doesn't allow database operations run in the Main thread note photo

    public void insertNotePhoto(NotePhoto note){
        new InsetNoteAsyncTask(notePhotoDao).execute(note);
    }

    public void deleteNotePhoto(NotePhoto note){
        new DeleteAsyncTask(notePhotoDao).execute(note);
    }

    public void updateNotePhoto(NotePhoto note){
        new UpdateAsyncTask(notePhotoDao).execute(note);
    }

    public void deleteAllNotePhoto(){
        new DeleteAllNoteAsyncTask(notePhotoDao).execute();
    }




    /**
     * Get forecasts data
     *
     * @return LiveData object to be notified when data change
     */
    public LiveData<NotePhoto> getNotePhotoInfo() {
        // Get LiveData object from database using Room
        return mAppDatabase.notePhotoDao().getAllNotePhotos();
    }

    public LiveData<CheckNote> getCheckNoteInfo() {
        // Get LiveData object from database using Room
        return mAppDatabase.checkNoteDao().getAllNoteCheck();
    }



    // Insert AsyncTask
    private static class InsetNoteAsyncTask extends AsyncTask<NotePhoto,Void,Void> {

        private NotePhotoDao notePhotoDao;
        private CheckNoteDao checkNoteDao;

        private InsetNoteAsyncTask(NotePhotoDao notePhotoDao){
            this.notePhotoDao = notePhotoDao;
            this.notePhotoDao = notePhotoDao;
        }

        @Override
        protected Void doInBackground(NotePhoto... notes) {
            notePhotoDao.insert(notes[0]);
            return null;
        }
    }

    // Delete AsyncTask
    private static class DeleteAsyncTask extends AsyncTask<NotePhoto, Void, Void>{

        private  NotePhotoDao notePhotoDao;

        private DeleteAsyncTask(NotePhotoDao noteDAO){
            this.notePhotoDao = noteDAO;
        }

        @Override
        protected Void doInBackground(NotePhoto... notes) {
            notePhotoDao.delete(notes[0]);
            return null;
        }
    }

    // Update AsyncTask
    private static class UpdateAsyncTask extends AsyncTask<NotePhoto, Void, Void>{

        private NotePhotoDao notePhotoDao;

        private UpdateAsyncTask(NotePhotoDao notePhotoDao){
            this.notePhotoDao = notePhotoDao;
        }

        @Override
        protected Void doInBackground(NotePhoto... notes) {
            notePhotoDao.update(notes[0]);
            return null;
        }
    }

    // Delete All Notes AsyncTask
    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void> {

        private NotePhotoDao notePhotoDao;

        private DeleteAllNoteAsyncTask(NotePhotoDao notePhotoDao){
            this.notePhotoDao = notePhotoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notePhotoDao.deleteAll();
            return null;
        }
    }

}