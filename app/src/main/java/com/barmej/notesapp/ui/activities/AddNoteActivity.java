package com.barmej.notesapp.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.barmej.notesapp.R;
import com.barmej.notesapp.data.model.CheckNote;
import com.barmej.notesapp.data.model.NotePhoto;
import com.barmej.notesapp.data.model.Notes;
import com.barmej.notesapp.viewmodel.NoteModelView;


public class AddNoteActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 123;
    private static final int REQUEST_CODE_PHOTO = 100;
    private static final String TAG = AddNoteActivity.class.getSimpleName();
    private CardView mCardViewPhoto, mCardViewNote, mCardViewCheckNote;
    private ImageView photoImageView;
    private Uri photoImageUri;
    private TextView photoNoteEditText, noteEditText, checkNoteEditText;
    private CheckBox checkNoteCheckBox;
    private NoteModelView noteModelView;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        noteModelView = ViewModelProviders.of(this).get(NoteModelView.class);
        init();

        //=========== function change type card view  note ============//

        //fun show card view photo
        findViewById(R.id.buCardViewPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardViewPhoto.setVisibility(View.VISIBLE);
                mCardViewNote.setVisibility(View.GONE);
                mCardViewCheckNote.setVisibility(View.GONE);


            }
        });

        // fun show card view simple note
        findViewById(R.id.buCardViewNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCardViewPhoto.setVisibility(View.GONE);
                mCardViewNote.setVisibility(View.VISIBLE);
                mCardViewCheckNote.setVisibility(View.GONE);

            }
        });

        // fun show card view check note
        findViewById(R.id.buCardViewCheckNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCardViewPhoto.setVisibility(View.GONE);
                mCardViewNote.setVisibility(View.GONE);
                mCardViewCheckNote.setVisibility(View.VISIBLE);


            }
        });


        //=========== function change  color card view  ============//

        //fun change color card view red
        findViewById(R.id.buColorRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorCardView(Color.rgb(220, 84, 75));

            }
        });
        //fun change color card view yellow
        findViewById(R.id.buColorYellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorCardView(Color.rgb(254, 193, 48));
            }
        });

        //fun change color card view blue
        findViewById(R.id.buColorBlue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorCardView(Color.rgb(225, 245, 253));


            }
        });

        // fun submit
        findViewById(R.id.button_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitData();
            }
        });


        // checkbox
        findViewById(R.id.checkNoteCheckBox).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (checkNoteCheckBox.isChecked()) {
                    checkNoteCheckBox.setChecked(true);

                } else {
                    checkNoteCheckBox.setChecked(false);
                    int noteCheckColor = mCardViewCheckNote.getCardBackgroundColor().getDefaultColor();
                    mCardViewCheckNote.getBackground().setTint(noteCheckColor);

                }
            }
        });


    }


    private void checkEmptyCardViewNotes() {
        if (mCardViewNote.getVisibility() == View.VISIBLE) {

            if (!noteEditText.getText().toString().isEmpty()) {

                insertSimpleNote();


            } else {
                Toast.makeText(getApplicationContext(), R.string.validate_message_note_text, Toast.LENGTH_SHORT).show();
            }
        }


        if (mCardViewCheckNote.getVisibility() == View.VISIBLE) {

            if (!checkNoteEditText.getText().toString().isEmpty()) {


                insertNoteChecked();


            } else {

                Toast.makeText(getApplicationContext(), R.string.validate_message_note_text, Toast.LENGTH_SHORT).show();
            }
        }

        if (mCardViewPhoto.getVisibility() == View.VISIBLE) {
            if (!photoNoteEditText.getText().toString().isEmpty()) {

                insertNotePhoto();


            } else {
                Toast.makeText(getApplicationContext(), R.string.validate_message_note_photo, Toast.LENGTH_SHORT).show();


            }
        }


    }


    private void submitData() {


        checkEmptyCardViewNotes();


    }


    private void insertSimpleNote() {
        String textNote = noteEditText.getText().toString();
        int noteSimpleColor = mCardViewNote.getCardBackgroundColor().getDefaultColor();

        Notes notes = new Notes(textNote, noteSimpleColor);
        if (notes != null) {
            noteModelView.insertSimpleNotes(notes);
            finish();
            Toast.makeText(this, "note saved", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(this, "note field", Toast.LENGTH_SHORT).show();

        }
    }

    private void insertNoteChecked() {
        //text  note check
        String textNoteCheck = checkNoteEditText.getText().toString();
        int noteCheckColor = mCardViewCheckNote.getCardBackgroundColor().getDefaultColor();
        CheckNote checkNote = new CheckNote(textNoteCheck, noteCheckColor, checkNoteCheckBox.isChecked());

        if (checkNote != null) {
            noteModelView.insertNoteCheck(checkNote);
            finish();
            Toast.makeText(this, "note saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "note field", Toast.LENGTH_SHORT).show();

        }
    }


    private void insertNotePhoto() {

        String photoTextNote = photoNoteEditText.getText().toString();
        int notePhotoColor = mCardViewPhoto.getCardBackgroundColor().getDefaultColor();
        NotePhoto notePhoto = new NotePhoto(photoTextNote, photoImageUri, notePhotoColor);

        if (notePhoto != null) {
            noteModelView.insertNotePhoto(notePhoto);
            finish();
            Log.v(TAG, "note data : " + notePhoto);
            Toast.makeText(this, "note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "note field", Toast.LENGTH_SHORT).show();

        }


    }


    private void changeColorCardView(int color) {

        mCardViewPhoto.setCardBackgroundColor(color);
        mCardViewNote.setCardBackgroundColor(color);
        mCardViewCheckNote.setCardBackgroundColor(color);


    }

    private void init() {

        //cardView
        mCardViewCheckNote = findViewById(R.id.cardViewCheckNote);
        mCardViewNote = findViewById(R.id.cardViewNote);
        mCardViewPhoto = findViewById(R.id.cardViewPhoto);
        // note photo
        photoImageView = findViewById(R.id.photoImageView);
        photoNoteEditText = findViewById(R.id.photoNoteEditText);
        // note simple
        noteEditText = findViewById(R.id.noteEditText);
        // note check
        checkNoteCheckBox = findViewById(R.id.checkNoteCheckBox);
        checkNoteEditText = findViewById(R.id.checkNoteEditText);

    }

    public void openGallery(View view) {

        checkPermissionPhoto();


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PHOTO) {

            if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                selectPhotoUri(data.getData());
                getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION);


            } else {

                Toast.makeText(getApplicationContext(), R.string.field_to_get_image, Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                selectPhoto();


            } else {

                Toast.makeText(getApplicationContext(), R.string.not_permission_select_photo, Toast.LENGTH_SHORT).show();


            }

        }
    }

    private void checkPermissionPhoto() {

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(AddNoteActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);

        } else {

            selectPhoto();
        }


    }


    private void selectPhoto() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_photo)), REQUEST_CODE_PHOTO);
    }

    private void selectPhotoUri(Uri data) {
        photoImageUri = data;
        photoImageView.setImageURI(this.photoImageUri);

    }
}
