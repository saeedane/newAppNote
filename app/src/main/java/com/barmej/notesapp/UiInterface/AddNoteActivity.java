package com.barmej.notesapp.UiInterface;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.barmej.notesapp.Constant;
import com.barmej.notesapp.Model.CheckNote;
import com.barmej.notesapp.R;


public class AddNoteActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 123;
    private static final int REQUEST_CODE_PHOTO = 100;
    CardView mCardViewPhoto,mCardViewNote,mCardViewCheckNote;
    ImageView photoImageView;
    Uri photoImageUri;
    TextView photoNoteEditText,noteEditText,checkNoteEditText;
    CheckBox checkNoteCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
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
                changeColorCardView(Color.rgb(220,84,75));

            }
        });
        //fun change color card view yellow
        findViewById(R.id.buColorYellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorCardView(Color.rgb(254,193,48));
            }
        });

        //fun change color card view blue
        findViewById(R.id.buColorBlue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColorCardView(Color.rgb(225,245,  253));


            }
        });

        // fun submit
        findViewById(R.id.button_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitData();
            }
        });






    }



    private void extraSetResultValue(int resultOk) {
        Intent intent = new Intent();
        intent.putExtra(Constant.EXTRA_URI_PHOTO, photoImageUri);
        intent.putExtra(Constant.EXTRA_TEXT_PHOTO, photoNoteEditText.getText().toString());
        intent.putExtra(Constant.EXTRA_TEXT_CHECK_NOTE, checkNoteEditText.getText().toString());
        intent.putExtra(Constant.EXTRA_TEXT_NOTE, noteEditText.getText().toString());
        intent.putExtra(Constant.EXTRA_NOTE_CHECK_COLOR, mCardViewCheckNote.getCardBackgroundColor().getDefaultColor());
        intent.putExtra(Constant.EXTRA_NOTE_PHOTO_COLOR, mCardViewPhoto.getCardBackgroundColor().getDefaultColor());
        intent.putExtra(Constant.EXTRA_NOTE_COLOR, mCardViewNote.getCardBackgroundColor().getDefaultColor());
        setResult(resultOk, intent);
        finish();
    }







    private void submitData() {


        checkEmptyCardViewNotes();




    }

    private void checkEmptyCardViewNotes() {

        if (mCardViewNote.getVisibility() == View.VISIBLE){
            if (!noteEditText.getText().toString().isEmpty()){
                extraSetResultValue(Activity.RESULT_OK);

            }else{
                Toast.makeText(getApplicationContext(),"  النص مطلوب في مذكرة البسيطة    ",Toast.LENGTH_SHORT).show();
            }

        }

        if (mCardViewCheckNote.getVisibility() == View.VISIBLE){

            if (!checkNoteEditText.getText().toString().isEmpty()){

                extraSetResultValue(Activity.RESULT_OK);
            }else{

                Toast.makeText(getApplicationContext(),"  النص مطلوب في مذكرة مهام    ",Toast.LENGTH_SHORT).show();
            }
        }
        if (mCardViewPhoto.getVisibility()== View.VISIBLE) {

            if (photoImageUri != null && !photoNoteEditText.getText().toString().isEmpty()) {
                extraSetResultValue(Activity.RESULT_OK);


            } else {
                Toast.makeText(getApplicationContext(), " الصورة و النص مطلوب في مذكرة بالصورة   ", Toast.LENGTH_SHORT).show();


            }

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
        // imageView
        photoImageView = findViewById(R.id.photoImageView);
        //textView
        photoNoteEditText = findViewById(R.id.photoNoteEditText);
        noteEditText = findViewById(R.id.noteEditText);
        checkNoteEditText = findViewById(R.id.checkNoteEditText);
        // checkbox
        checkNoteCheckBox = findViewById(R.id.checkNoteCheckBox);

    }

    public void openGallery(View view) {

        checkPermissionPhoto();



    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){

                selectPhoto();


            }else{

                Toast.makeText(getApplicationContext(),"عذرا لا تملك صلاحية لفتح معرض الصور ",Toast.LENGTH_SHORT).show();


            }

        }
    }

    private void checkPermissionPhoto() {

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(AddNoteActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION);

        }else{

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

        if (requestCode == REQUEST_CODE_PHOTO){

            if (resultCode == RESULT_OK && data != null && data.getData() != null ){


                selectPhotoUri(data);



            }

        }

    }

    private void selectPhotoUri(Intent data) {
        photoImageUri = data.getData();
        photoImageView.setImageURI(this.photoImageUri);

    }
}
