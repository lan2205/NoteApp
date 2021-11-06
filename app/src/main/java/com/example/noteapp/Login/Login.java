package com.example.noteapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.noteapp.ForgotPassword.ForgotPassword;
import com.example.noteapp.Home.Home;
import com.example.noteapp.R;
import com.example.noteapp.Register.Register;

public class Login extends AppCompatActivity {

    private TextView tv_signup;
    private TextView tv_forgot;
    private EditText Username, Password;
    private ImageView iv_show_hide_password;
    private Button btN_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_forgot = (TextView) findViewById(R.id.tv_forgot);
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        tv_signup = (TextView) findViewById(R.id.tv_signup);
        iv_show_hide_password = (ImageView) findViewById(R.id.ShowHide_PW);
        btN_Login = (Button) findViewById(R.id.btn_signin);

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
                String User = "tienxinxan";
                String pass = "123";
                if (Username.getText().toString().equals(User) && Password.getText().toString().equals(pass)) {
                    Toast.makeText(getApplicationContext(), "Successfull! Welcome " +User, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong account or password", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
