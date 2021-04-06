package com.barmej.notesapp.UiInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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


    private static final int REQUEST_ADD_DATA = 200;
    RecyclerNoteAdapter mAdapter;
    RecyclerView mRecyler;
    ArrayList<Items> mItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyler = findViewById(R.id.recycler_view_photos);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyler.setHasFixedSize(true);
        mRecyler.setLayoutManager(staggeredGridLayoutManager);
        mItems = new ArrayList<>();
        mAdapter = new RecyclerNoteAdapter(mItems);
        mRecyler.setAdapter(mAdapter);






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

                Uri imagePhoto = data.getParcelableExtra(Constant.EXTRA_URI_PHOTO);
                Toast.makeText(getApplicationContext()," "+data.getData(),Toast.LENGTH_SHORT).show();
                String photoText = data.getParcelableExtra(Constant.EXTRA_TEXT_PHOTO);
                NotePhoto notePhoto = new NotePhoto(photoText,imagePhoto);
                addItem(notePhoto);

            }

        }
    }



    private void addItem(NotePhoto notePhoto) {
        mItems.add(new Items(1,notePhoto));
        mAdapter.notifyItemInserted(mItems.size() - 1);

    }
}
