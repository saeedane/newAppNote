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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.barmej.notesapp.Adapter.RecyclerNoteAdapter;
import com.barmej.notesapp.Constant;
import com.barmej.notesapp.Model.Items;
import com.barmej.notesapp.Model.NotePhoto;
import com.barmej.notesapp.R;

public class UpdateNotePhotoDetails extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_photo_details);

        final Uri imageUri =  getIntent().getParcelableExtra(Constant.EXTRA_URI_PHOTO);
        ((ImageView)findViewById(R.id.photoImageView)).setImageURI(imageUri);
        final String textNotePhoto = getIntent().getStringExtra(Constant.EXTRA_TEXT_PHOTO);
        ((EditText)findViewById(R.id.photoNoteEditText)).setText(textNotePhoto);
        final int position = getIntent().getIntExtra(Constant.EXTRA_NOTE_POSITION,0);
        ((Button)findViewById(R.id.button_update)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ImageView imageView = findViewById(R.id.photoImageView);
                EditText editText = findViewById(R.id.photoNoteEditText);
                String photoNoteEditText  = editText.getText().toString();
                NotePhoto notePhoto = new NotePhoto(photoNoteEditText,imageUri,Color.rgb(225,245,  253));
                editText.setText(notePhoto.getNoteBodyPhoto());



            }
        });
    }




}
