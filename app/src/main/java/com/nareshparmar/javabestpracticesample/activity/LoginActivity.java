package com.nareshparmar.javabestpracticesample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nareshparmar.javabestpracticesample.R;
import com.nareshparmar.javabestpracticesample.database.AppDatabase;
import com.nareshparmar.javabestpracticesample.database.MySharePreferences;
import com.nareshparmar.javabestpracticesample.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText etLoginPassword,etLoginEmail;
    TextView tvLoginRegisterNow;
    AppDatabase appDatabase;
    MySharePreferences mySharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initAllControls();
    }

    private void initAllControls()
    {
        btnLogin=findViewById(R.id.btnLogin);
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword=findViewById(R.id.etLoginPassword);
        btnLogin.setOnClickListener(this);
        tvLoginRegisterNow = findViewById(R.id.tvLoginRegisterNow);
        tvLoginRegisterNow.setOnClickListener(this);
        appDatabase = AppDatabase.getAppDatabase(this);
        mySharePreferences = new MySharePreferences(this);
        if(mySharePreferences.isUserLogin())
        {
            startHomeActivity();
        }
    }

    @Override
    public void onClick(View v) {
        if(v==btnLogin) {
            String email = etLoginEmail.getText().toString();
            String password = etLoginPassword.getText().toString();
            if (email.trim().isEmpty()) {
                Toast.makeText(this, "Please enter email address", Toast.LENGTH_SHORT).show();
            } else if (password.trim().isEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            } else {
                verifyUserIsValid(email,password);
            }
        }else
        {
            Intent intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }

    public void verifyUserIsValid(String email,String password)
    {
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void[] objects) {
                User user = appDatabase.userDao().verifyUser(email,password);

                if (user != null) {
                    mySharePreferences.saveUserDetail(user);
                    return 1;
                } else {
                    return 2;

                }

            }

            @Override
            protected void onPostExecute(Integer aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean == 1) {
                    Toast.makeText(LoginActivity.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                    startHomeActivity();
                } else if (aBoolean == 2) {
                    Toast.makeText(LoginActivity.this, "Email & Password not match", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }.execute();
    }

    public void startHomeActivity()
    {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


}