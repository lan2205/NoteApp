package com.example.noteapp.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.noteapp.R;
import com.example.noteapp.Register.Register;
import com.example.noteapp.Register.Register1;
import com.example.noteapp.Search;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    private ImageView home_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_search = (ImageView) findViewById(R.id.home_search);

        home_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Search.class);
                startActivity(intent);
            }
        });
    }
}