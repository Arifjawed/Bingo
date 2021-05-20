package com.example.tasksample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    //    @Override
   /* public void onbackpress()
    {
        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }*/
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  webView = findViewById(R.id.webviewid);
        Intent intent = getIntent();
        if (intent.hasExtra("urls")) {
            //String keyweb = intent.getStringExtra("key");
            String urlweb = intent.getStringExtra("urls");
            Log.i("applink",urlweb);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("http://"+urlweb);

        } else {
            Toast.makeText(this, "There is no url from server side.", 5000).show();
        }*/
    }
}