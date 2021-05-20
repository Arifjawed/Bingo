package com.example.tasksample;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SessionManager {
    SharedPreferences sharedPreferences;
    Context context;
    boolean isLogin = false;

    public SessionManager(Context context) {
        this.context = context;
        init();
    }

    public void init() {
        sharedPreferences = this.context.getSharedPreferences(this.context.getPackageName(), Context.MODE_PRIVATE);
    }

    public void setLogin(boolean login) {
        sharedPreferences.edit().putBoolean("Login", login).commit();
    }
    //above code general for all Seasionmanager


    public boolean isLogin() {

        return sharedPreferences.getBoolean("Login", false);
    }

    public void setDashboardData(DeshboardUser dashboardData) {
        String json = new Gson().toJson(dashboardData);
        sharedPreferences.edit().putString("dashboardData", json).commit();
    }

    public String getDashboardData() {
        return sharedPreferences.getString("dashboardData", null);
    }

    public void setUserData(Myuser login) {
        String json = new Gson().toJson(login);
        sharedPreferences.edit().putString("userData", json).commit();
    }

    public String getUserData() {
        return sharedPreferences.getString("userData", null);
    }

    public void setUser(String user) {
        sharedPreferences.edit().putString("user", user).commit();
    }

    public String getUser() {
        return sharedPreferences.getString("user", null);
    }

}
