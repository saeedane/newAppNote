package com.barmej.notesapp.UiInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.barmej.notesapp.Adapter.RecyclerNoteAdapter;
import com.barmej.notesapp.Constant;
import com.barmej.notesapp.Interface.OnItemLongClickListener;
import com.barmej.notesapp.Model.CheckNote;
import com.barmej.notesapp.Model.Items;
import com.barmej.notesapp.Model.NotePhoto;
import com.barmej.notesapp.Model.Notes;
import com.barmej.notesapp.R;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_ADD_DATA = 101;
    public static RecyclerNoteAdapter mAdapter;
    private RecyclerView mRecyclerNote;
    public static ArrayList<Items> mItems;
    private int notesColor, noteCheckColor, notePhotoColor;
    private Uri imagePhoto;
    private String photoTextNote, textNote, textNoteCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerNote = findViewById(R.id.recycler_view_photos);
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
        startActivityForResult(intent, REQUEST_ADD_DATA);

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
        imagePhoto = data.getParcelableExtra(Constant.EXTRA_URI_PHOTO);
        photoTextNote = data.getStringExtra(Constant.EXTRA_TEXT_PHOTO);
        NotePhoto notePhoto = new NotePhoto(photoTextNote, imagePhoto, notePhotoColor);
        addItemNotePhoto(notePhoto);
        // text note
        textNote = data.getStringExtra(Constant.EXTRA_TEXT_NOTE);
        Notes notes = new Notes(textNote, notesColor);
        addItemNotes(notes);
        //text  note check
        textNoteCheck = data.getStringExtra(Constant.EXTRA_TEXT_CHECK_NOTE);
        CheckNote NoteCheck = new CheckNote(textNoteCheck, noteCheckColor);
        addItemNoteCheck(NoteCheck);


    }

    private void getExtraColorCardView(Intent data) {
        // get color card view
        notesColor = data.getIntExtra(Constant.EXTRA_NOTE_COLOR, 0);
        noteCheckColor = data.getIntExtra(Constant.EXTRA_NOTE_CHECK_COLOR, 0);
        notePhotoColor = data.getIntExtra(Constant.EXTRA_NOTE_PHOTO_COLOR, 0);
    }

    private void addItemNotes(Notes notes) {
        if (!notes.getNoteBodySimple().isEmpty()) {
            mItems.add(new Items(0, notes));
            mAdapter.notifyItemInserted(mItems.size() - 1);
        }

    }

    private void addItemNotePhoto(NotePhoto notePhoto) {
        if (notePhoto.getNoteImage() != null && notePhoto.getNoteBodyPhoto() != null) {
            mItems.add(new Items(1, notePhoto));
            mAdapter.notifyItemInserted(mItems.size() - 1);
        }

    }

    private void addItemNoteCheck(CheckNote noteCheck) {
        if (!noteCheck.getNoteBodyCheck().isEmpty()) {
            mItems.add(new Items(2, noteCheck));
            mAdapter.notifyItemInserted(mItems.size() - 1);

        }


    }


}




