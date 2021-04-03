package com.barmej.notesapp.UiInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.barmej.notesapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickAddNoteActivity(View view) {
        startActivity(new Intent(MainActivity.this,AddNoteActivity.class));
    }
}
