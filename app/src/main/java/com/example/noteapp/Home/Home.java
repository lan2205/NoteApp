package com.example.noteapp.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.noteapp.R;
import com.example.noteapp.Search;

public class Home extends AppCompatActivity {

    private ImageView img_NewNote;
    private ImageView img_Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        img_NewNote = (ImageView) findViewById(R.id.newNote);
        img_NewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, CreateNote.class);
                startActivity(intent);
            }
        });
        img_Search = (ImageView) findViewById(R.id.Search);
        img_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Search.class);
                startActivity(intent);
            }
        });
    }
}