package com.example.noteapp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.example.noteapp.Login.Login;
import com.example.noteapp.R;

public class Settings extends AppCompatActivity {

    private ImageView ic_back;
    private TextView tv_logout;
    private Button btn_setpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        ic_back = (ImageView) findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, Home.class);
                startActivity(intent);
            }
        });

        tv_logout = (TextView) findViewById(R.id.tv_logout);
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, Login.class);
                startActivity(intent);
            }
        });

        btn_setpassword = (Button) findViewById(R.id.btn_setpassword);
        btn_setpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, SetPassword.class);
                startActivity(intent);
            }
        });

    }

}