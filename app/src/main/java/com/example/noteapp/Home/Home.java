package com.example.noteapp.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.noteapp.R;

public class Home extends AppCompatActivity {

    private ImageView img_NewNote;
    private ImageView ic_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        img_NewNote = (ImageView) findViewById(R.id.newNote);
        ic_menu = (ImageView) findViewById(R.id.ic_menu);

        ic_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Settings.class);
                startActivity(intent);
            }
        });

        img_NewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, CreateNote.class);
                startActivity(intent);
            }
        });
    }
}