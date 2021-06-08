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
import android.view.View;
import android.widget.Toast;

import com.barmej.notesapp.ui.Adapter.RecyclerNoteAdapter;
import com.barmej.notesapp.ui.Constant;
import com.barmej.notesapp.ui.Interface.OnItemLongClickListener;
import com.barmej.notesapp.data.Model.CheckNote;
import com.barmej.notesapp.data.Model.Items;
import com.barmej.notesapp.data.Model.NotePhoto;
import com.barmej.notesapp.data.Model.Notes;
import com.barmej.notesapp.R;
import com.barmej.notesapp.viewmodel.NoteModelView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_ADD_DATA = 101;
    public static RecyclerNoteAdapter mAdapter;
    private RecyclerView mRecyclerNote;
    public static ArrayList<Items> mItems;
    private int notesColor, noteCheckColor, notePhotoColor;
    private Uri imagePhoto;
    private boolean NoteCheckBox;
    private NoteModelView noteModelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerNote = findViewById(R.id.recycler_view_photos);
        noteModelView = ViewModelProviders.of(this).get(NoteModelView.class);

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
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("هل أنت متأكد من حذف هذا العنصر ");
        dialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try {
                    mItems.remove(position);
                    mAdapter.notifyItemRemoved(position);
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
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        noteModelView.getAllNotes().observe(this, new Observer<NotePhoto>() {
            @Override
            public void onChanged(NotePhoto notePhoto) {
                addItemNotePhoto(notePhoto);
                mAdapter.notifyDataSetChanged();

            }
        });


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
        getExtraColorCardView(data);
        // image uri
        Uri image = data.getParcelableExtra(Constant.EXTRA_URI_PHOTO);
        String photoTextNote = data.getStringExtra(Constant.EXTRA_TEXT_PHOTO);

        if (image != null){
            NotePhoto notePhoto = new NotePhoto(photoTextNote, image, notePhotoColor);
            noteModelView.insert(notePhoto);

            Toast.makeText(this, "note saved", Toast.LENGTH_SHORT).show();


        }
        // text note
        String textNote = data.getStringExtra(Constant.EXTRA_TEXT_NOTE);
        if (textNote != null) {
            Notes notes = new Notes(textNote, notesColor);
            //addItemNotes(notes);
        }
        //text  note check
        String textNoteCheck = data.getStringExtra(Constant.EXTRA_TEXT_CHECK_NOTE);
        NoteCheckBox = data.getBooleanExtra(Constant.EXTRA_IS_CHECK_NOTE,false);
        if (textNoteCheck != null) {
            CheckNote NoteCheck = new CheckNote(textNoteCheck, noteCheckColor, NoteCheckBox);
           // addItemNoteCheck(NoteCheck);
        }


    }

    private void getExtraColorCardView(Intent data) {
        // get color card view
        notesColor = data.getIntExtra(Constant.EXTRA_NOTE_COLOR, 0);
        noteCheckColor = data.getIntExtra(Constant.EXTRA_NOTE_CHECK_COLOR, 0);
        notePhotoColor = data.getIntExtra(Constant.EXTRA_NOTE_PHOTO_COLOR, 0);
    }

    /*
    private void addItemNotes(Notes notes) {
            mItems.add(new Items(0, notes));
           mAdapter.notifyItemInserted(mItems.size() - 1);


    }

    */


    private void addItemNotePhoto(NotePhoto notePhoto) {

            mItems.add(new Items(1, notePhoto));


    }

    /*

    private void addItemNoteCheck(CheckNote noteCheck) {
            mItems.add(new Items(2, noteCheck));
            mAdapter.notifyItemInserted(mItems.size() - 1);




    }
*/

}




