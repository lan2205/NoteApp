package com.example.noteapp.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.noteapp.R;

public class CreateNote extends AppCompatActivity {

    private ImageView img_backHome;
    private ImageView img_More;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        img_backHome = (ImageView) findViewById(R.id.imageBack);
        img_More = (ImageView) findViewById(R.id.more_actions);

        img_backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNote.this, Home.class);
                startActivity(intent);
            }
        });
        img_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNote.this, CreateNote.class);
                startActivity(intent);
            }
        });
    }
}