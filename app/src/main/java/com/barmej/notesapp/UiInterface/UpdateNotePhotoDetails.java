package com.barmej.notesapp.UiInterface;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.barmej.notesapp.Adapter.RecyclerNoteAdapter;
import com.barmej.notesapp.Constant;
import com.barmej.notesapp.Model.Items;
import com.barmej.notesapp.Model.NotePhoto;
import com.barmej.notesapp.R;

public class UpdateNotePhotoDetails extends AppCompatActivity {
    private static final int REQUEST_CODE_PHOTO = 101;
    private ImageView photoImageView;
    private EditText photoNoteEditText;
    private String photoNoteText;
    private Uri imgPhotoUri;
    private CardView card_view_photos;
    private int colorNotePhoto,position;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_photo_details);

        // Definition of variable
        init();
        // receive image uri item
        Uri imgPhotoUriExtra = getIntent().getParcelableExtra(Constant.EXTRA_URI_PHOTO);
        photoImageView.setImageURI(imgPhotoUriExtra);
        imgPhotoUri = imgPhotoUriExtra;
        // receive text note photo item
        String photoNoteText = getIntent().getStringExtra(Constant.EXTRA_TEXT_PHOTO);
        photoNoteEditText.setText(photoNoteText);
        // receive position item
        position = getIntent().getIntExtra(Constant.EXTRA_NOTE_POSITION, 0);
        // receive color item
        colorNotePhoto = getIntent().getIntExtra(Constant.EXTRA_NOTE_PHOTO_COLOR, 0);
        card_view_photos.setBackgroundColor(colorNotePhoto);


    }


    private void init() {

        photoImageView = findViewById(R.id.photoImageView);
        photoNoteEditText = findViewById(R.id.photoNoteEditText);
        card_view_photos = findViewById(R.id.card_view_photos);

    }


    public void openGallery(View view) {

        selectPhoto();


    }





    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PHOTO) {

            if (resultCode == RESULT_OK && data != null && data.getData() != null) {

                selectPhotoUri(data.getData());
                getContentResolver().takePersistableUriPermission(data.getData(),Intent.FLAG_GRANT_READ_URI_PERMISSION);

            } else {

                Toast.makeText(getApplicationContext(), R.string.field_to_get_image, Toast.LENGTH_SHORT).show();

            }


        }


    }

    private void selectPhoto() {


        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_photo)), REQUEST_CODE_PHOTO);
    }

    private void selectPhotoUri(Uri data) {

        imgPhotoUri = data;
        photoImageView.setImageURI(imgPhotoUri);

    }


    @Override
    public void onBackPressed() {
        if (!photoNoteEditText.getText().toString().isEmpty() && imgPhotoUri != null) {
            super.onBackPressed();
            UpdateNotePhoto();

        } else {

            Toast.makeText(getApplicationContext(), R.string.validate_message_note_photo, Toast.LENGTH_SHORT).show();
        }

    }

    private void UpdateNotePhoto() {

        photoNoteText = photoNoteEditText.getText().toString();
        NotePhoto notePhoto = new NotePhoto(photoNoteText, imgPhotoUri, colorNotePhoto);
        MainActivity.mItems.add(new Items(1, notePhoto));
        MainActivity.mItems.remove(position);
        MainActivity.mAdapter.notifyItemChanged(position);
        Toast.makeText(getApplicationContext(), R.string.success_message_notes, Toast.LENGTH_SHORT).show();
        finish();


    }
}
