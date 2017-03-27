package com.be.ubihomes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Main3Activity extends ActionBarActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        webView = (WebView) findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://ubiquitoushomes.com/");

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("NAME");
        String type_length = bundle.getString("LEN");
        String type_height = bundle.getString("HEI");
        Toast.makeText(Main3Activity.this, "Category: "+name+"\n Length: "+type_length+"\n Height: "+type_height,Toast.LENGTH_SHORT).show();

    }
}
