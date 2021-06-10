package com.barmej.notesapp.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.barmej.notesapp.data.model.CheckNote;
import com.barmej.notesapp.data.model.Notes;
import com.barmej.notesapp.ui.Adapter.RecyclerNoteAdapter;
import com.barmej.notesapp.ui.Adapter.Listener.OnItemLongClickListener;
import com.barmej.notesapp.data.model.Items;
import com.barmej.notesapp.data.model.NotePhoto;
import com.barmej.notesapp.R;
import com.barmej.notesapp.viewmodel.NoteModelView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_ADD_DATA = 101;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static RecyclerNoteAdapter mAdapter;
    private RecyclerView mRecyclerNote;
    public static ArrayList<Items> mItems;
    //private int notesColor, noteCheckColor, notePhotoColor;
    //private Uri imagePhoto;
    private boolean NoteCheckBox;
    private NoteModelView noteModelView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerNote = findViewById(R.id.recycler_view_photos);
        noteModelView = ViewModelProviders.of(this).get(NoteModelView.class);
        noteModelView.getAllSimpleNote().observe(this, new Observer<Notes>() {
            @Override
            public void onChanged(Notes notes) {
                mAdapter.setNote(notes,0);

            }
        });


        noteModelView.getAllNotePhoto().observe(this, new Observer<NotePhoto>() {
            @Override
            public void onChanged(NotePhoto notePhotos) {

                mAdapter.setNote(notePhotos,1);
            }
        });


        noteModelView.getAllNoteCheck().observe(this, new Observer<CheckNote>() {
            @Override
            public void onChanged(CheckNote checkNote) {
                mAdapter.setNote(checkNote,2);
            }
        });
        mItems = new ArrayList<>();
        mAdapter = new RecyclerNoteAdapter(mItems, new OnItemLongClickListener() {
            @Override
            public void onLongItem(int position) {

                deleteItem(position);

            }
        });


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerNote.setHasFixedSize(true);
        mRecyclerNote.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerNote.setAdapter(mAdapter);

    }



    private void deleteItem(final int position) {
         AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("هل أنت متأكد من حذف هذا العنصر ");
        dialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try {
                    noteModelView.deleteAllNotePhoto();
                    mAdapter.notifyItemRemoved(position);
                    mItems.remove(position);
                    mAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                dialogInterface.dismiss();
            }
        }).create();
        dialog.show();

    }

    public void clickAddNoteActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        startActivityForResult(intent,REQUEST_ADD_DATA);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_DATA) {

            if (resultCode == RESULT_OK && data != null) {

                getExtraDataNote(data);

            }else{

                Toast.makeText(getApplicationContext(),R.string.select_data_note,Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void getExtraDataNote(Intent data) {
        //getExtraColorCardView(data);
        insertNotePhoto(data);
        // text note
       insertSimpleNote(data);

        // checked note
        insertNoteChecked(data);


    }

    private void insertSimpleNote(Intent data) {
        String textNote = data.getStringExtra(Constant.EXTRA_TEXT_NOTE);
        int noteSimpleColor = data.getIntExtra(Constant.EXTRA_NOTE_COLOR,0);

        Notes notes = new Notes(textNote, noteSimpleColor);
        if (notes != null) {
            noteModelView.insertSimpleNotes(notes);
            Toast.makeText(this, "note saved", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(this, "note field", Toast.LENGTH_SHORT).show();

        }
    }

    private void insertNoteChecked(Intent data) {

        //text  note check
        String textNoteCheck = data.getStringExtra(Constant.EXTRA_TEXT_CHECK_NOTE);
        boolean NoteCheckBox = data.getBooleanExtra(Constant.EXTRA_IS_CHECK_NOTE,false);
        int notePhotoColor = data.getIntExtra(Constant.EXTRA_NOTE_PHOTO_COLOR,0);

        CheckNote checkNote = new CheckNote(textNoteCheck, notePhotoColor);

        if (checkNote != null){
            noteModelView.insertNoteCheck(checkNote);
            Toast.makeText(this, "note saved", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "note field", Toast.LENGTH_SHORT).show();

        }
    }

    /*
    private void getExtraColorCardView(Intent data) {
        // get color card view
        notesColor = data.getIntExtra(Constant.EXTRA_NOTE_COLOR, 0);
        noteCheckColor = data.getIntExtra(Constant.EXTRA_NOTE_CHECK_COLOR, 0);
        notePhotoColor = data.getIntExtra(Constant.EXTRA_NOTE_PHOTO_COLOR, 0);
    }


     */

    /*
    private void addItemNotes(Notes notes) {
            mItems.add(new Items(0, notes));
           mAdapter.notifyItemInserted(mItems.size() - 1);


    }

    */


    private void insertNotePhoto(Intent data) {

        // image uri
        Uri image = data.getParcelableExtra(Constant.EXTRA_URI_PHOTO);
        String photoTextNote = data.getStringExtra(Constant.EXTRA_TEXT_PHOTO);
        int notePhotoColor = data.getIntExtra(Constant.EXTRA_NOTE_PHOTO_COLOR,0);

        NotePhoto notePhoto = new NotePhoto(photoTextNote, image, notePhotoColor);

        if (notePhoto != null){
            noteModelView.insertNotePhoto(notePhoto);
            Log.v(TAG,"note data : " + notePhoto);
            Toast.makeText(this, "note saved", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "note field", Toast.LENGTH_SHORT).show();

        }


    }

    /*

    private void addItemNoteCheck(CheckNote noteCheck) {
            mItems.add(new Items(2, noteCheck));
            mAdapter.notifyItemInserted(mItems.size() - 1);




    }
*/

}




