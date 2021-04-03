package com.barmej.notesapp.UiInterface;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.barmej.notesapp.R;


public class AddNoteActivity extends AppCompatActivity {
    CardView mCardViewPhoto,mCardViewNote,mCardViewCheckNote;
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






    }

    private void changeColorCardView(int color) {

        mCardViewPhoto.getBackground().setTint(color);
        mCardViewNote.getBackground().setTint(color);
        mCardViewCheckNote.getBackground().setTint(color);

    }

    private void init() {

        mCardViewCheckNote = findViewById(R.id.cardViewCheckNote);
        mCardViewNote = findViewById(R.id.cardViewNote);
        mCardViewPhoto = findViewById(R.id.cardViewPhoto);

    }

}
