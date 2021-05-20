package com.example.tasksample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class dashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView deshboardtv;
    BottomNavigationView bottomview;
    WebView webView; //add webview
    private static int PICK_CONTACT = 1;
    int check = 0;
    //recyclerview
    RecyclerView recyclerView; //recyclerview
    RecyclerView.LayoutManager layoutManager;//A LayoutManager is responsible for measuring and positioning item views within a RecyclerView
    RecyclerView.Adapter madapter;
    String urlss = "http://bysbs.com/ak2/keyapin.php";
    static String values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        webView = findViewById(R.id.webviewid); //for webview
        bottomview = findViewById(R.id.bottomNav); //bottomview initialization
        deshboardtv = findViewById(R.id.tvdeshid); //textview
        recyclerView = (RecyclerView) findViewById(R.id.recyid);
        bottomview.setOnNavigationItemSelectedListener(this); //bottom view listner
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarid);
        toolbar.setTitle("Dashboard");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getweburl(); //webview function call here

    }


    //for webview getdata function
    public void getweburl() {
        Intent intent = getIntent();
        if (intent.hasExtra("urls")) {
            check = 0;
            String urlweb = intent.getStringExtra("urls");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("http://" + urlweb);

        } else {
            Toast.makeText(this, "There is no url from server side.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listitems, menu);
        return true;

    }

    //one item select listener for menu bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_about_id:
                aboutfunction();
                return true;
            case R.id.item_notification_id:
                notififuction(getIntent().getExtras().getString("getKey"));
                return true;
            case R.id.logout_id:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Logout in dashboard
    private void logout() {
        SessionManager session = new SessionManager(this);
        session.setLogin(false);
        session.setDashboardData(null);
        session.setUserData(null);
        session.setUser(null);
        Intent intent = new Intent(this, EnterKey.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Successfully Logout", Toast.LENGTH_SHORT).show();
    }

    //about function
    private void aboutfunction() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            check = 1;
            webView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            deshboardtv.setVisibility(View.VISIBLE);
            deshboardtv.setText("");
            String value = extras.getString("about");
            deshboardtv.setText(value);

            //The key argument here must match that used in the other activity
        } else {
            Toast.makeText(this, " something wrong", Toast.LENGTH_SHORT).show();
        }
    }

    //notification function for all get data of notification Api in this func
    private void notififuction(String key) {

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext()); //synchornous mean queue
        StringRequest request = new StringRequest(Request.Method.GET, urlss, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Type collectionType = new TypeToken<List<DeshboardUser>>() {
                }.getType();
                List<DeshboardUser> user = gson.fromJson(response, collectionType); //arraylist use for
                ArrayList<DeshboardUser> deshboardUsers = new ArrayList<>();
                deshboardUsers.clear();
                for (DeshboardUser userdata : user) {
                    if (user != null && key.equalsIgnoreCase(userdata.getAppkey())) {
                        deshboardUsers.add(userdata);
                        check = 2;
                        deshboardtv.setVisibility(View.GONE);//check=1
                        webView.setVisibility(View.GONE);//check=0
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                }
                madapter = new com.example.tasksample.adapternotification(deshboardUsers);
                recyclerView.setAdapter(madapter);
                madapter.notifyDataSetChanged();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volleyError", error.getMessage());
            }
        });
        requestQueue.add(request);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.naviwhatsapp:
                whatsappfunc();
                Toast.makeText(this, "WhatsApp", Toast.LENGTH_LONG).show();
                break;
            case R.id.navimap:
                mapfunction();
                Toast.makeText(this, "Map", Toast.LENGTH_LONG).show();
                break;
            case R.id.navicontact:
                contactfunction();
                Toast.makeText(this, "Contact", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    //whatsapp function........................................................................................
    private void whatsappfunc() {
        Bundle extras = getIntent().getExtras();
        String value = extras.getString("whatsap");
        boolean installed = appInstalled("com.whatsapp");
        if (installed) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+92" + value));
            startActivity(intent);
        } else {
            Toast.makeText(this, " something wrong", Toast.LENGTH_SHORT).show();
        }
        //The key argument here must match that used in the other activity
    }


    //its another appInstalled function using
    private boolean appInstalled(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
    //close whatsapp function.......................................................................


    //map function
    public void mapfunction() {
        Bundle extras = getIntent().getExtras();
        String value = extras.getString("map");
        if (value != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(value));
            startActivity(intent);
        } else {
            Toast.makeText(this, " something wrong", Toast.LENGTH_SHORT).show();
        }

    }

    //Contact function
    public void contactfunction() {
        makephonepermision();

    }

    //phone work start
    public void makephonepermision() {
        if (ContextCompat.checkSelfPermission(dashboard.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(dashboard.this, new String[]{Manifest.permission.CALL_PHONE}, PICK_CONTACT);

        } else {
            Bundle bundle = getIntent().getExtras();
            String pickcontacts = bundle.getString("phone");
            if (pickcontacts != null) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + pickcontacts)); //NOTE we have to give permission into android application open apne mobile se lazmi
                startActivity(intent);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PICK_CONTACT && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            makephonepermision();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }


    }
    //phone work finsh
    //onbackpress work

    public void onBackPressed() {
        if (check == 0) {
            finish();
        } else if (check == 1 || check == 2)  //about1
        {
            recyclerView.setVisibility(View.GONE);
            deshboardtv.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        } else {
            finish();
        }
    }
}







