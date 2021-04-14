package com.barmej.notesapp.UiInterface;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.barmej.notesapp.Adapter.RecyclerNoteAdapter;
import com.barmej.notesapp.Constant;
import com.barmej.notesapp.Model.Items;
import com.barmej.notesapp.Model.NotePhoto;
import com.barmej.notesapp.R;

public class UpdateNotePhotoDetails extends AppCompatActivity {
    private ImageView photoImageView;
    private EditText photoNoteEditText;
    private int position;
    private String photoNoteText;
    private Uri imgPhotoUri;
    private RelativeLayout mContent;
    private int colorNotePhoto;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_photo_details);

        init();
        // receive image uri item
        imgPhotoUri =  getIntent().getParcelableExtra(Constant.EXTRA_URI_PHOTO);
        photoImageView.setImageURI(imgPhotoUri);
        // receive text note photo item
        photoNoteEditText.setText(photoNoteText);
        // receive position item
        position = getIntent().getIntExtra(Constant.EXTRA_NOTE_POSITION,0);
        // receive color item
        colorNotePhoto = getIntent().getIntExtra(Constant.EXTRA_NOTE_PHOTO_COLOR, 0);
        mContent.setBackgroundColor(colorNotePhoto);


        //(findViewById(R.id.button_update)).setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View view) {




          //  }
       // });
    }

    @Override
    public void onBackPressed() {
        if (!photoNoteEditText.getText().toString().isEmpty()) {
            photoNoteText = photoNoteEditText.getText().toString();
            super.onBackPressed();
            NotePhoto notePhoto = new NotePhoto(photoNoteText, imgPhotoUri, colorNotePhoto);
            MainActivity.mItems.add(new Items(1, notePhoto));
            photoNoteEditText.setText(notePhoto.getNoteBodyPhoto());
            MainActivity.mItems.remove(position);
            MainActivity.mAdapter.notifyItemChanged(position);
            Toast.makeText(getApplicationContext(),"  تم تحديث بيانات التذكرة  ",Toast.LENGTH_SHORT).show();
            finish();

        }else{

            Toast.makeText(getApplicationContext()," لا تترك نص الملاحظة فارغ  ",Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {

        photoImageView = findViewById(R.id.photoImageView);
        photoNoteEditText = findViewById(R.id.photoNoteEditText);
        mContent = findViewById(R.id.relative_view_photos);

    }


}
