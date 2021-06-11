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
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_ADD_DATA = 101;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static RecyclerNoteAdapter mAdapter;
    private RecyclerView mRecyclerNote;
    public static ArrayList<Items> mItems;
    private int notesColor, noteCheckColor, notePhotoColor;
    private boolean NoteCheckBox;
    private NoteModelView noteModelView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerNote = findViewById(R.id.recycler_view_photos);
        noteModelView = ViewModelProviders.of(this).get(NoteModelView.class);
        showAllNoteData();
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

                    noteModelView.deleteAllNoteCheck();
                    noteModelView.deleteAllNoteCheck();
                    noteModelView.deleteAllNoteCheck();
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
        startActivity(intent);

    }


    private void showAllNoteData() {

        noteModelView.getAllSimpleNote().observe(this, new Observer<Notes>() {
            @Override
            public void onChanged(Notes notes) {
                setSimpleNote(notes);


            }
        });


        noteModelView.getAllNotePhoto().observe(this, new Observer<NotePhoto>() {
            @Override
            public void onChanged(NotePhoto notePhotos) {

                setNotePhoto(notePhotos);

            }
        });


        noteModelView.getAllNoteCheck().observe(this, new Observer<CheckNote>() {
            @Override
            public void onChanged(CheckNote checkNote) {
                setCheckNote(checkNote);


            }
        });
    }


    public void setSimpleNote(Notes note) {
        if (note != null) {
            mItems.clear();
            mItems.add(new Items(note, 0));
            mAdapter.notifyDataSetChanged();

        }

    }


    public void setNotePhoto(NotePhoto notePhoto) {
        if (notePhoto != null) {
            mItems.clear();
            mItems.add(new Items(notePhoto, 1));
            mAdapter.notifyDataSetChanged();

        }

    }


    public void setCheckNote(CheckNote checkNote) {
        if (checkNote != null) {
            mItems.clear();
            mItems.add(new Items(checkNote, 2));
            mAdapter.notifyDataSetChanged();

        }

    }


}




