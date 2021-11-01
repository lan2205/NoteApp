package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgotPassword1 extends AppCompatActivity {
    private Button btn_continue_forgot1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password1);
        btn_continue_forgot1 = (Button) findViewById(R.id.btn_continue_forgot1);

        btn_continue_forgot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword1.this,ForgotPassword2.class);
                startActivity(intent);
            }
        });
    }
}