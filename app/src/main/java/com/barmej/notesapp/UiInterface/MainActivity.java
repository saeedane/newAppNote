package com.barmej.notesapp.UiInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.barmej.notesapp.Adapter.RecyclerNoteAdapter;
import com.barmej.notesapp.Constant;
import com.barmej.notesapp.Model.CheckNote;
import com.barmej.notesapp.Model.Items;
import com.barmej.notesapp.Model.NotePhoto;
import com.barmej.notesapp.Model.Notes;
import com.barmej.notesapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_ADD_DATA = 101;
    RecyclerNoteAdapter mAdapter;
    RecyclerView mRecyclerNote;
    ArrayList<Items> mItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerNote = findViewById(R.id.recycler_view_photos);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerNote.setHasFixedSize(true);
        mRecyclerNote.setLayoutManager(staggeredGridLayoutManager);
        mItems = new ArrayList<>();
        mAdapter = new RecyclerNoteAdapter(mItems);
        mRecyclerNote.setAdapter(mAdapter);






    }

    public void clickAddNoteActivity(View view) {
        Intent intent = new  Intent(MainActivity.this,AddNoteActivity.class);
        startActivityForResult(intent,REQUEST_ADD_DATA);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_DATA){

            if (resultCode == RESULT_OK && data != null ){



                getExtraDataNote(data);



            }

        }
    }

    private void getExtraDataNote(Intent data) {
        // image uri
        Uri imagePhoto = data.getParcelableExtra(Constant.EXTRA_URI_PHOTO);
        String photoText = data.getStringExtra(Constant.EXTRA_TEXT_PHOTO);
        NotePhoto notePhoto = new NotePhoto(photoText,imagePhoto);
        addItemNotePhoto(notePhoto);
        // text note
        String textNote = data.getStringExtra(Constant.EXTRA_TEXT_NOTE);
        Toast.makeText(getApplicationContext()," "+textNote,Toast.LENGTH_SHORT).show();
        Notes notes = new Notes(textNote);
        addItemNotes(notes);
        //text  note check
        String textNoteCheck = data.getStringExtra(Constant.EXTRA_TEXT_CHECK_NOTE);
        CheckNote NoteCheck = new CheckNote(textNoteCheck,false);
        addItemNoteCheck(NoteCheck);

    }

    private void addItemNotes(Notes notes) {
        mItems.add(new Items(0,notes));
        mAdapter.notifyItemChanged(mItems.size() - 2);

    }

    private void addItemNotePhoto(NotePhoto notePhoto) {
        mItems.add(new Items(1,notePhoto));
        mAdapter.notifyItemChanged(mItems.size() - 2);

    }

    private void addItemNoteCheck(CheckNote noteCheck) {


        mItems.add(new Items(2,noteCheck));
        mAdapter.notifyItemChanged(mItems.size() - 2);


    }




}
