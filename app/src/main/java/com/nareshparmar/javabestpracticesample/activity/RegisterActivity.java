package com.nareshparmar.javabestpracticesample.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nareshparmar.javabestpracticesample.R;
import com.nareshparmar.javabestpracticesample.database.AppDatabase;
import com.nareshparmar.javabestpracticesample.model.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etRegisterName, etRegisterEmail, etRegisterMobile, etRegisterPassword, etRegisterConfirmPassword;
    private Button btnRegister;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initAllControls();
    }

    private void initAllControls() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etRegisterName = findViewById(R.id.etRegisterName);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterMobile = findViewById(R.id.etRegisterMobile);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        appDatabase = AppDatabase.getAppDatabase(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String name = etRegisterName.getText().toString();
        String email = etRegisterEmail.getText().toString();
        String mobile = etRegisterMobile.getText().toString();
        String password = etRegisterPassword.getText().toString();
        String confirmPassword = etRegisterConfirmPassword.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Please enter email address", Toast.LENGTH_SHORT).show();
        } else if (mobile.isEmpty()) {
            Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
        } else if (confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please enter confirm Password", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Please check confirm password not match", Toast.LENGTH_SHORT).show();
        } else {
            onSubmitValueForRegister(name,email,mobile,password);
        }
    }

    private void onSubmitValueForRegister(String name, String email, String mobile, String password) {
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void[] objects) {
                User user = appDatabase.userDao().checkEmailExiest(email);
                if (user != null) {
                    return 1;
                } else {
                    User userInsert = new User();
                    userInsert.email = email;
                    userInsert.name = name;
                    userInsert.mobile = mobile;
                    userInsert.password = password;
                    long inserted = appDatabase.userDao().insertUser(userInsert);
                    if (inserted > 0) {
                        return 2;
                    } else {
                        return 3;
                    }
                }

            }

            @Override
            protected void onPostExecute(Integer aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean == 1) {
                    Toast.makeText(RegisterActivity.this, "User Already Exiest", Toast.LENGTH_SHORT).show();
                } else if (aBoolean == 2) {
                    Toast.makeText(RegisterActivity.this, "Register Sucessfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Something went to wrong please try again", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }


}