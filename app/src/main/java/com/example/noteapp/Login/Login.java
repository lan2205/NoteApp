package com.example.noteapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.noteapp.ForgotPassword.ForgotPassword;
import com.example.noteapp.Home.Home;
import com.example.noteapp.R;
import com.example.noteapp.Register.Register;

public class Login extends AppCompatActivity {

    private TextView tv_signup;
    private TextView tv_forgot;
    private ImageView iv_show_hide_password;
    private Button btN_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_forgot = (TextView) findViewById(R.id.tv_forgot);
        tv_signup = (TextView) findViewById(R.id.tv_signup);
        iv_show_hide_password = (ImageView) findViewById(R.id.ShowHide_PW);
        btN_Login = (Button)findViewById(R.id.btn_signin);
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
            });

        tv_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
        btN_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
            }
        });
        }
}
