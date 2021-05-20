package com.example.tasksample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.Dash;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class splashscreen extends Activity {
    ImageView logoImage, backgroundImage;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        sessionManager = new SessionManager(this);
        Thread th = new Thread() {
            public void run() {
                try {

                    sleep(5000);

                } catch (Exception ex) {
                    ex.printStackTrace();

                } finally {
                    if (sessionManager.isLogin()) {
                        Intent intent = new Intent(splashscreen.this, dashboard.class);
                        DeshboardUser globaldeshUser2 = new Gson().fromJson(sessionManager.getDashboardData(), DeshboardUser.class);
                        Myuser globalMyuser1 = new Gson().fromJson(sessionManager.getUserData(), Myuser.class);
                        intent.putExtra("nid", globaldeshUser2.getNid());
                        intent.putExtra("appkey", globaldeshUser2.getAppkey());
                        intent.putExtra("ntext", globaldeshUser2.getNtext());
                        intent.putExtra("ncd", globaldeshUser2.getNcd());
                        intent.putExtra("ned", globaldeshUser2.getNed());

                        intent.putExtra("getKey", sessionManager.getUser());

                        intent.putExtra("urls", globalMyuser1.getApplink());
                        intent.putExtra("about", globalMyuser1.getAbout());
                        intent.putExtra("logo", globalMyuser1.getLogourl());
                        intent.putExtra("map", globalMyuser1.getMap());
                        intent.putExtra("phone", globalMyuser1.getPh());
                        intent.putExtra("whatsap", globalMyuser1.getWap());
                        intent.putExtra("bgurls", globalMyuser1.getBgurl());
                        sessionManager.setLogin(true);
                        sessionManager.setDashboardData(globaldeshUser2);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(splashscreen.this, EnterKey.class);
                        startActivity(intent);
                    }

                }
            }

        };
        th.start();
    }
}