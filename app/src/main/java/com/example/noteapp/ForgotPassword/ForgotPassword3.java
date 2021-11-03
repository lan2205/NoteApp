package com.example.noteapp.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.noteapp.Login.Login;
import com.example.noteapp.R;

public class ForgotPassword3 extends AppCompatActivity {
    private Button btn_donepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password3);

        btn_donepass = (Button) findViewById(R.id.btn_donepass);
        btn_donepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword3.this, Login.class);
                startActivity(intent);
            }
        });
    }
}