package com.example.noteapp.Home;

import androidx.appcompat.app.AppCompatActivity;
import com.example.noteapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Trash extends AppCompatActivity {

    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        iv_back = (ImageView) findViewById(R.id.ic_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trash.this,Settings.class);
                startActivity(intent);
            }
        });
    }
}