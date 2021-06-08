package com.barmej.notesapp.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.barmej.notesapp.ui.Constant;
import com.barmej.notesapp.data.Model.Items;
import com.barmej.notesapp.data.Model.Notes;
import com.barmej.notesapp.R;

public class UpdateNoteDetails extends AppCompatActivity {

    private EditText noteEditText;
    private String noteText;
    private CardView cardViewNote;
    private int colorBackground,position;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);


        // receive background color item
        colorBackground = getIntent().getIntExtra(Constant.EXTRA_NOTE_COLOR, 0);
        cardViewNote = findViewById(R.id.cardViewNote);
        cardViewNote.getBackground().setTint(colorBackground);
        // receive note text item
        noteEditText = findViewById(R.id.noteEditText);
        noteText = getIntent().getStringExtra(Constant.EXTRA_TEXT_NOTE);
        // print text note
        noteEditText.setText(noteText);
        // receive  position item
        position = getIntent().getIntExtra(Constant.EXTRA_NOTE_POSITION, 0);


    }


    @Override
    public void onBackPressed() {
        if (!noteEditText.getText().toString().isEmpty()) {
            super.onBackPressed();
            updateCheckNote();
        } else {

            Toast.makeText(getApplicationContext(), R.string.validate_message_note_text, Toast.LENGTH_SHORT).show();

        }
    }

    private void updateCheckNote() {

        String noteText = noteEditText.getText().toString();
        Notes notes = new Notes(noteText, colorBackground);
        MainActivity.mItems.add(new Items(0, notes));
        MainActivity.mItems.remove(position);
        MainActivity.mAdapter.notifyItemChanged(position);
        MainActivity.mAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), R.string.success_message_notes, Toast.LENGTH_SHORT).show();
        finish();

    }


}
