package com.barmej.notesapp.UiInterface;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.barmej.notesapp.Adapter.RecyclerNoteAdapter;
import com.barmej.notesapp.Constant;
import com.barmej.notesapp.Model.Items;
import com.barmej.notesapp.Model.NotePhoto;
import com.barmej.notesapp.R;

public class UpdateNotePhotoDetails extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 100;
    private static final int REQUEST_CODE_PHOTO = 101;
    private ImageView photoImageView;
    private EditText photoNoteEditText;
    private int position;
    private String photoNoteText;
    private Uri imgPhotoUri;
    private RelativeLayout mContent;
    private int colorNotePhoto;
    private NotePhoto notePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_photo_details);

        init();
        // receive image uri item
        Uri imgPhotoUriExtra =  getIntent().getParcelableExtra(Constant.EXTRA_URI_PHOTO);
        photoImageView.setImageURI(imgPhotoUriExtra);
        // receive text note photo item
        photoNoteEditText.setText(notePhoto.getNoteBodyPhoto() );
        // receive position item
        position = getIntent().getIntExtra(Constant.EXTRA_NOTE_POSITION,0);
        // receive color item
        colorNotePhoto = getIntent().getIntExtra(Constant.EXTRA_NOTE_PHOTO_COLOR, 0);
        mContent.setBackgroundColor(colorNotePhoto);



    }




    private void init() {

        photoImageView = findViewById(R.id.photoImageView);
        photoNoteEditText = findViewById(R.id.photoNoteEditText);
        mContent = findViewById(R.id.relative_view_photos);

    }


    public void openGallery(View view) {

        checkPermissionPhoto();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                selectPhoto();

            }else{

                Toast.makeText(getApplicationContext(),"عذرا لا تملك صلاحية لفتح معرض الصور ",Toast.LENGTH_SHORT).show();



            }

        }
    }

    private void checkPermissionPhoto() {


        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION);
        }else {

            selectPhoto();
        }


    }

    private void selectPhoto() {


        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(intent,REQUEST_CODE_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PHOTO ){

            if (resultCode == RESULT_OK && data != null && data.getData() != null ){

                selectPhotoUri(data);

            }else{

                Toast.makeText(getApplicationContext(),"يجب عليك اضافة صورة  ",Toast.LENGTH_SHORT).show();

            }


        }


    }

    private void selectPhotoUri(Intent data) {

        imgPhotoUri= data.getData();
        photoImageView.setImageURI(imgPhotoUri);

    }


    @Override
    public void onBackPressed() {
        if (!photoNoteEditText.getText().toString().isEmpty()) {
            super.onBackPressed();

            UpdateNotePhoto();

        }else{

            Toast.makeText(getApplicationContext()," لا تترك نص الملاحظة فارغ  ",Toast.LENGTH_SHORT).show();
        }

    }

    private void UpdateNotePhoto() {

        photoNoteText = photoNoteEditText.getText().toString();
        notePhoto = new NotePhoto(photoNoteText, imgPhotoUri, colorNotePhoto);
        MainActivity.mItems.add(new Items(1, notePhoto));
        MainActivity.mItems.remove(position);
        MainActivity.mAdapter.notifyItemChanged(position);
        Toast.makeText(getApplicationContext(),"  تم تحديث بيانات التذكرة  ",Toast.LENGTH_SHORT).show();
        finish();
    }
}
