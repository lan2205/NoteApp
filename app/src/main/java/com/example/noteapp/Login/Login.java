package com.example.noteapp.Login;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteapp.ForgotPassword.ForgotPassword;
import com.example.noteapp.Home.Home;
import com.example.noteapp.R;
import com.example.noteapp.Register.Register;
import com.example.noteapp.User.ApiService;
import com.example.noteapp.User.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private TextView tv_signup;
    private TextView tv_forgot;
    private ImageView iv_show_hide_password;
    private Button btN_Login;
    private EditText Username,Password;

    private List<User> mListUser;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        tv_forgot = (TextView) findViewById(R.id.tv_forgot);
        tv_signup = (TextView) findViewById(R.id.tv_signup);
        iv_show_hide_password = (ImageView) findViewById(R.id.ShowHide_PW);
        btN_Login = (Button)findViewById(R.id.btn_signin);
        mListUser = new ArrayList<>();

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

        GetListUser();
        btN_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickLogin();

            }
        });
    }

    private void ClickLogin(){
        String strUsername = Username.getText().toString().trim();
        String strPassword = Password.getText().toString().trim();

        if(mListUser == null || mListUser.isEmpty()){
            return;
        }

        boolean isHasUser = false;
        for(User user: mListUser){
            if(strUsername.equals(user.getUsername()) && strPassword.equals(user.getPass_word())){
                isHasUser = true;
                mUser = user;
                break;
            }
        }
        if(isHasUser){
            Intent intent = new Intent(Login.this,Home.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_user",mUser);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            Toast.makeText(Login.this, "UserName or Password invalid ! ", Toast.LENGTH_SHORT).show();
        }
    }
    private void GetListUser(){
        ApiService.apiService.getUser()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        mListUser = response.body();
                        Log.e("List User",mListUser.size()+ "");
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(Login.this, "Call API error!!!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
