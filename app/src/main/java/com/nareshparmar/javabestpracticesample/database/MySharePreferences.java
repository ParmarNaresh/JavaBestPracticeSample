package com.nareshparmar.javabestpracticesample.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.nareshparmar.javabestpracticesample.model.User;

public class MySharePreferences {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public MySharePreferences(Context context){
        sharedPreferences = context.getSharedPreferences("app_name",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUserDetail(User user)
    {
        editor.putBoolean("is_login",true);
        editor.putInt("user_id",user.userId);
        editor.putString("name", user.name);
        editor.putString("email", user.email);
        editor.putString("mobile",user.mobile);
        editor.putString("password", user.password);
        editor.commit();
    }

    public Boolean isUserLogin()
    {
        return sharedPreferences.getBoolean("is_login", false);
    }

    public int getUserId()
    {
        return sharedPreferences.getInt("user_id",0);
    }

    public String getUserName()
    {
        return sharedPreferences.getString("name","");
    }
    public String getUserEmail()
    {
        return sharedPreferences.getString("email", "");
    }
    public String getUserMobile()
    {
        return sharedPreferences.getString("mobile","");
    }

    public String getUserPassword()
    {
        return sharedPreferences.getString("password", "");
    }



}
