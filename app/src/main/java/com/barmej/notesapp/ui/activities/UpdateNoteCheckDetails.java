package com.barmej.notesapp.ui.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.barmej.notesapp.ui.Constant;
import com.barmej.notesapp.data.database.model.CheckNote;
import com.barmej.notesapp.data.database.model.Items;
import com.barmej.notesapp.R;

public class UpdateNoteCheckDetails extends AppCompatActivity {
    private EditText checkNoteEditText;
    private String checkNoteText;
    private Boolean isChecked;
    private CheckBox checkBox;
    private int noteCheckColor,position;
    private CardView cardViewNoteCheck;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_check_details);
        init();

        // receive background color  item
        noteCheckColor = getIntent().getIntExtra(Constant.EXTRA_NOTE_CHECK_COLOR, 0);
        cardViewNoteCheck.getBackground().setTint(noteCheckColor);
        // receive text note item
        checkNoteText = getIntent().getStringExtra(Constant.EXTRA_TEXT_CHECK_NOTE);
        checkNoteEditText.setText(checkNoteText);
        // receive status checkbox
        isChecked = getIntent().getBooleanExtra(Constant.EXTRA_IS_CHECK_NOTE, false);
        checkBox.setChecked(isChecked);
        // receive int position item
        position = getIntent().getIntExtra(Constant.EXTRA_NOTE_POSITION, 0);

        if (isChecked == true) {

            cardViewNoteCheck.getBackground().setTint(Color.rgb(76, 175, 80));


        } else {
            cardViewNoteCheck.getBackground().setTint(noteCheckColor);


        }


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {

                    checkBox.setChecked(true);
                    cardViewNoteCheck.getBackground().setTint(Color.rgb(76, 175, 80));

                } else {
                    checkBox.setChecked(false);
                    cardViewNoteCheck.getBackground().setTint(noteCheckColor);

                }
            }
        });


    }

    private void init() {
        cardViewNoteCheck = findViewById(R.id.cardViewNoteCheck);
        checkNoteEditText = findViewById(R.id.checkNoteEditText);
        checkBox = findViewById(R.id.checkNoteCheckBox);
    }

    @Override
    public void onBackPressed() {
        if (!checkNoteEditText.getText().toString().isEmpty()) {
            super.onBackPressed();
            updateCheckNote();
        } else {

            Toast.makeText(getApplicationContext(), R.string.validate_message_note_text, Toast.LENGTH_SHORT).show();

        }
    }


    private void updateCheckNote() {

        checkNoteText = checkNoteEditText.getText().toString();
        CheckNote checkNote = new CheckNote(checkNoteText, noteCheckColor);
//        MainActivity.mItems.add(new Items(2, checkNote));
        MainActivity.mItems.remove(position);
        MainActivity.mAdapter.notifyItemChanged(position);
        MainActivity.mAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), R.string.success_message_notes, Toast.LENGTH_SHORT).show();
        finish();

    }


}
