package com.barmej.notesapp.UiInterface;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.barmej.notesapp.Adapter.RecyclerNoteAdapter;
import com.barmej.notesapp.Constant;
import com.barmej.notesapp.Model.CheckNote;
import com.barmej.notesapp.Model.Items;
import com.barmej.notesapp.Model.NotePhoto;
import com.barmej.notesapp.R;

public class UpdateNoteCheckDetails extends AppCompatActivity {
    private EditText checkNoteEditText;
    private String checkNoteText;
    private Boolean isChecked;
    private CheckBox checkBox;
    private int noteCheckColor;
    private ConstraintLayout cardViewNoteCheck;
    int position;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_check_details);
        cardViewNoteCheck =  findViewById(R.id.cardViewNoteCheck);
        noteCheckColor = getIntent().getIntExtra(Constant.EXTRA_NOTE_CHECK_COLOR,0);
        cardViewNoteCheck.getBackground().setTint(noteCheckColor);
        checkNoteEditText = findViewById(R.id.checkNoteEditText);
        checkBox = findViewById(R.id.checkNoteCheckBox);
        checkNoteText = getIntent().getStringExtra(Constant.EXTRA_TEXT_CHECK_NOTE);
        isChecked = getIntent().getBooleanExtra(Constant.EXTRA_IS_CHECK_NOTE,false);
        checkNoteEditText.setText(checkNoteText);
        checkBox.setChecked(isChecked);
        position = getIntent().getIntExtra(Constant.EXTRA_NOTE_POSITION,0);

        if (isChecked == true){

            cardViewNoteCheck.getBackground().setTint(Color.rgb(76,175,  80));


        }else{
            cardViewNoteCheck.getBackground().setTint(noteCheckColor);



        }


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()){

                    checkBox.setChecked(true);
                    cardViewNoteCheck.getBackground().setTint(Color.rgb(76,175,  80));

                }else{
                    checkBox.setChecked(false);
                    cardViewNoteCheck.getBackground().setTint(noteCheckColor);

                }
            }
        });






    }

    @Override
    public void onBackPressed() {
        if (!checkNoteEditText.getText().toString().isEmpty()){
            super.onBackPressed();
            updateCheckNote();
        }else{

            Toast.makeText(getApplicationContext(), "  نص مذكرة مطلوب حتي يتم تحديث المذكرة   ", Toast.LENGTH_SHORT).show();

        }
    }

    private void updateCheckNote() {

        checkNoteText = checkNoteEditText.getText().toString();

        CheckNote checkNote = new CheckNote(checkNoteText,noteCheckColor,checkBox.isChecked());
        MainActivity.mItems.add(new Items(2, checkNote));
        MainActivity.mItems.remove(position);
        MainActivity.mAdapter.notifyItemChanged(position);
        MainActivity.mAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), R.string.success_message_notes, Toast.LENGTH_SHORT).show();
        finish();

    }



}
