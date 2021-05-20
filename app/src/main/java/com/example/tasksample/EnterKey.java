package com.example.tasksample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.List;

public class EnterKey extends Activity {

    ImageView backgroundimage, logoimage;
    EditText E_text;
    Button dashboardbtn;
    Myuser globalMyuser; //complete API weburl,about
    DeshboardUser globaldeshUser; //notification API
    Boolean check = false;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ener_key);
        sessionManager = new SessionManager(this);

        E_text = findViewById(R.id.enterkey_id);
        dashboardbtn = findViewById(R.id.join_dashboar_id_);

        // 2nd button dashboard api call from here
        dashboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = new ProgressDialog(EnterKey.this);
                dialog.setMessage("Please wait...");
                dialog.setCancelable(false);
                dialog.show();

                String dashburl = "http://bysbs.com/ak2/keyapin.php"; //notification API
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, dashburl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(EnterKey.this, "success"+response.toString(), Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        Log.i("tab", "successfull" + response);
                        String getKey = E_text.getText().toString().trim();
                        if (TextUtils.isEmpty(getKey)) //usign validation
                        {
                            E_text.setError("Please fill the Key");
                            return;
                        }

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        Type collectionType = new TypeToken<List<DeshboardUser>>() {
                        }.getType();
                        List<DeshboardUser> user = gson.fromJson(response, collectionType);
                        for (DeshboardUser u : user) {
                            if (getKey.equalsIgnoreCase(u.getAppkey())) {
                                globaldeshUser = u;
                                check = true;
                            }
                        }
                        if (check) {
                            Toast.makeText(EnterKey.this, "Successfully login", Toast.LENGTH_SHORT).show();
                            enterlog();
                            //add this one for dialogue box

                        } else {
                            Toast.makeText(EnterKey.this, "please Enter Right Key", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        Toast.makeText(EnterKey.this, "error" + error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
    });
                requestQueue.add(stringRequest);
            }
        });

    }

    //this is a enterlog function for webview 2nd API
    public void enterlog() {

        String url = "https://bysbs.com/ak2/keyapi.php"; //2ndApi for weburl all
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(EnerKey.this, "success"+response.toString(), Toast.LENGTH_SHORT).show();
                String getKey = E_text.getText().toString().trim();
                if (TextUtils.isEmpty(getKey)) //usign validation
                {
                    E_text.setError("Please fill the Key");
                    return;
                }

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Type collectionType = new TypeToken<List<Myuser>>() {
                }.getType();
                List<Myuser> user = gson.fromJson(response, collectionType);
                for (Myuser u : user) {
                    if (getKey.equalsIgnoreCase(u.getAppkey())) {
                        globalMyuser = u;
                        check = true;
                    }
                }
                if (check) {
                    Toast.makeText(EnterKey.this, "Successfully login", Toast.LENGTH_SHORT).show();
                    alertdialogbox(globalMyuser, globaldeshUser, getKey);
                } else {
                    Toast.makeText(EnterKey.this, "please Enter Right Key", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EnterKey.this, "Check Enternet Connction" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(stringRequest);
    }


    //for alert dialogbox
    public void alertdialogbox(Myuser globalMyuser1, DeshboardUser globaldeshUser2, String getKey) {

        //  Toast.makeText(EnerKey.this, "success"+response.toString(), Toast.LENGTH_SHORT).show();
        final AlertDialog.Builder alert = new AlertDialog.Builder(EnterKey.this);
        View mview = getLayoutInflater().inflate(R.layout.alertdialogue, null);
        backgroundimage = (ImageView) mview.findViewById(R.id.backgroundImageid);
        logoimage = (ImageView) mview.findViewById(R.id.logoid);

        if (globalMyuser != null) {
            Glide.with(EnterKey.this).load("https://" + globalMyuser.getLogourl()).into(logoimage);
            Glide.with(EnterKey.this).load("https://" + globalMyuser.getBgurl()).into(backgroundimage);
        } else {
            Glide.with(EnterKey.this).load("https://i.pinimg.com/originals/7c/51/98/7c5198d2a0751fa76c8433dba4a1a12a.jpg").into(logoimage);
            Glide.with(EnterKey.this).load("http://i.imgur.com/DvpvklR.png").into(backgroundimage);
        }

//.........

        alert.setView(mview);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                    ex.printStackTrace();

                } finally {
                    sendToDashboard(globalMyuser1, globaldeshUser2, getKey);
                }
            }
        };
        thread.start();
        //i think its neccessary for a button in dialogue box thats why we use here

        alertDialog.show();

    }

    private void sendToDashboard(Myuser globalMyuser1, DeshboardUser globaldeshUser2, String getKey) {
        Intent intent = new Intent(EnterKey.this, dashboard.class);
        intent.putExtra("nid", globaldeshUser2.getNid());
        intent.putExtra("appkey", globaldeshUser2.getAppkey());
        intent.putExtra("ntext", globaldeshUser2.getNtext());
        intent.putExtra("ncd", globaldeshUser2.getNcd());
        intent.putExtra("ned", globaldeshUser2.getNed());
        intent.putExtra("getKey", getKey);

        intent.putExtra("urls", globalMyuser1.getApplink());
        intent.putExtra("about", globalMyuser1.getAbout());
        intent.putExtra("logo", globalMyuser1.getLogourl());
        intent.putExtra("map", globalMyuser1.getMap());
        intent.putExtra("phone", globalMyuser1.getPh());
        intent.putExtra("whatsap", globalMyuser1.getWap());
        intent.putExtra("bgurls", globalMyuser1.getBgurl());
        sessionManager.setLogin(true);
        sessionManager.setDashboardData(globaldeshUser2);
        sessionManager.setUserData(globalMyuser1);
        sessionManager.setUser(getKey);
        startActivity(intent);
    }


}


