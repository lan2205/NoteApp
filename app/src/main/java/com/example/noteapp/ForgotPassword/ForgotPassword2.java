package com.example.noteapp.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.noteapp.R;

public class ForgotPassword2 extends AppCompatActivity{
    private Button btn_changepass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);

        btn_changepass = (Button) findViewById(R.id.btn_changepass);
        btn_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword2.this, ForgotPassword3.class);
                startActivity(intent);
            }
        });
    }
}