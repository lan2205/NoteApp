package com.example.noteapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.noteapp.R;

public class Start extends AppCompatActivity {

    private Button btn_getstart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btn_getstart = (Button) findViewById(R.id.btn_getstart);

        btn_getstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start.this, Login.class);
                startActivity(intent);
            }
        });
    }


}