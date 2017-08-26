package com.be.ubihomes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Main5Activity extends ActionBarActivity {
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String loadthisurl = bundle.getString("LOAD4URL");
        setContentView(R.layout.activity_main5);
        WebView webView=(WebView)findViewById(R.id.buyWebView);
        webView.setWebViewClient(new WebViewClient());
        Log.e("URL","this "+loadthisurl);
        webView.loadUrl(loadthisurl);


    }
}
