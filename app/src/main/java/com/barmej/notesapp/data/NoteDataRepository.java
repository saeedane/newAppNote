package com.barmej.notesapp.data;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import androidx.lifecycle.LiveData;

import com.barmej.notesapp.data.Model.NotePhoto;
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

    }


    // Room doesn't allow database operations run in the Main thread

    public void insert(NotePhoto note){
        new InsetNoteAsyncTask(notePhotoDao).execute(note);
    }

    public void delete(NotePhoto note){
        new DeleteAsyncTask(notePhotoDao).execute(note);
    }

    public void update(NotePhoto note){
        new UpdateAsyncTask(notePhotoDao).execute(note);
    }

    public void deleteAllNotes(){
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

    /**
     * Empty weather info table and save new weather info to database
     */
    public void updateWeatherInfo() {


    }

    /**
     * Empty forecasts table and save new forecasts info to database
     */
    public void updateForecastLists() {



    }


    /**
     * Cancel all data requests
     */
    public void cancelDataRequests() {

    }


    // Insert AsyncTask
    private static class InsetNoteAsyncTask extends AsyncTask<NotePhoto,Void,Void> {

        private NotePhotoDao noteDAO;

        private InsetNoteAsyncTask(NotePhotoDao noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NotePhoto... notes) {
            noteDAO.insert(notes[0]);
            return null;
        }
    }

    // Delete AsyncTask
    private static class DeleteAsyncTask extends AsyncTask<NotePhoto, Void, Void>{

        private  NotePhotoDao noteDAO;

        private DeleteAsyncTask(NotePhotoDao noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NotePhoto... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }

    // Update AsyncTask
    private static class UpdateAsyncTask extends AsyncTask<NotePhoto, Void, Void>{

        private NotePhotoDao noteDAO;

        private UpdateAsyncTask(NotePhotoDao noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NotePhoto... notes) {
            noteDAO.update(notes[0]);
            return null;
        }
    }

    // Delete All Notes AsyncTask
    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void> {

        private NotePhotoDao noteDAO;

        private DeleteAllNoteAsyncTask(NotePhotoDao noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.deleteAll();
            return null;
        }
    }

}