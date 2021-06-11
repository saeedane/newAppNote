package com.barmej.notesapp.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import com.barmej.notesapp.data.model.Items;
import com.barmej.notesapp.data.model.Notes;
import com.barmej.notesapp.R;
import com.barmej.notesapp.viewmodel.NoteModelView;

public class UpdateNoteDetails extends AppCompatActivity {

    private EditText noteEditText;
    private String noteText;
    private CardView cardViewNote;
    private int colorBackground, position;
    private NoteModelView noteModelView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        // init view model
        noteModelView = ViewModelProviders.of(this).get(NoteModelView.class);
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
        if (notes != null) {
            noteModelView.updateSimpleNotes(notes);
            MainActivity.mItems.add(new Items(notes, 0));
            MainActivity.mItems.remove(position);
            MainActivity.mAdapter.notifyItemChanged(position);
            Toast.makeText(getApplicationContext(), R.string.success_message_notes, Toast.LENGTH_SHORT).show();
            finish();

        } else {
            Toast.makeText(getApplicationContext(), R.string.field_update_message_notes, Toast.LENGTH_SHORT).show();

        }


    }


}
