package com.example.noteapp.Home;

import androidx.appcompat.app.AppCompatActivity;
import com.example.noteapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SetPassword extends AppCompatActivity {

    private ImageView ic_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        ic_back = (ImageView) findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetPassword.this,Settings.class);
                startActivity(intent);
            }
        });
    }
}