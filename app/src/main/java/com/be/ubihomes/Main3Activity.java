package com.be.ubihomes;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

public class Main3Activity extends ActionBarActivity {
    WebView webView;
    ProgressBar progressBar;
    //Double dLength,dHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        webView = (WebView) findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("https://ubiquitoushomes.com/");

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("NAME");
        String type_length = bundle.getString("LEN");
        String type_height = bundle.getString("HEI");
        //Toast.makeText(Main3Activity.this, "Category: "+name+"\n Length: "+type_length+"\n Height: "+type_height,Toast.LENGTH_SHORT).show();

        Double dLength = Double.parseDouble(type_length);
        Double dHeight = Double.parseDouble(type_height);

        String Portrait_1=getResources().getString(R.string.Potrails10);
        String Portrait_2=getResources().getString(R.string.Potrails20);

        String Bed_1=getResources().getString(R.string.Bed180);
        String Bed_2=getResources().getString(R.string.Bed200);

        String Fridge_1=getResources().getString(R.string.Refrigerator30);
        String Fridge_2=getResources().getString(R.string.Refrigerator100);

        String Sofa_1=getResources().getString(R.string.Sofa140);
        String Sofa_2=getResources().getString(R.string.Sofa170);

        String Table_1=getResources().getString(R.string.Table40);
        String Table_2=getResources().getString(R.string.Table80);

        String TV_1=getResources().getString(R.string.TV20);
        String TV_2=getResources().getString(R.string.TV30);
        String TV_3=getResources().getString(R.string.TV40);
        String TV_4=getResources().getString(R.string.TV50);


        webView=(WebView)findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public  void onPageFinished(WebView view, String url){
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        progressBar=(ProgressBar)findViewById(R.id.progress_loader);



        //filtering logic
        switch(name) {

            case "Painting":
            {
                if(dLength<21&&dLength>11&&dHeight<20&&dHeight>11){
                    //webView.loadUrl("https://ubiquitoushomes.com/product-category/potraits/?filter_painting-size=10-to-20-inch");
                    webView.loadUrl(Portrait_1);
                }
                else if(dLength<24&&dLength>15&&dHeight<24&&dHeight>15) {
                    webView.loadUrl(Portrait_2);
                }
                else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ",Toast.LENGTH_SHORT).show();
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/potraits/");
                }
            }
           break;
            case "Bed":
            {
                if(dLength<200&&dLength>183&&dHeight<100&&dHeight>63.5){
                    webView.loadUrl(Bed_1);
                }
                else if(dLength<218.4&&dLength>213.4&&dHeight<109&&dHeight>102){
                    webView.loadUrl(Bed_2);
                }
                else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ",Toast.LENGTH_SHORT).show();
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/bed/");
                }

            }
            break;
            case "Fridge":
            {
                if(dLength<55.5&&dLength>30&&dHeight<147&&dHeight>36.1){
                    webView.loadUrl(Fridge_1);
                }
                else if(dLength<90.8&&dLength>43&&dHeight<179&&dHeight>79.6){
                    webView.loadUrl(Fridge_2);
                }
                else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ",Toast.LENGTH_SHORT).show();
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/refrigerator/");
                }
            }
            break;
            case "Sofa":
            {
                if(dLength<76&&dLength>72&&dHeight<147.8&&dHeight>142){
                    webView.loadUrl(Sofa_1);
                }
                else if(dLength<94&&dLength>82&&dHeight<230&&dHeight>152.4){
                    webView.loadUrl(Sofa_2);
                }
                else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ",Toast.LENGTH_SHORT).show();
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/sofa/");
                }
            }
            break;
            case "Table":
            {
                if(dLength<88&&dLength>45.72&&dHeight<58&&dHeight>40.64){
                    webView.loadUrl(Table_1);
                }
                else if(dLength<115&&dLength>101.8&&dHeight<75&&dHeight>50.8){
                    webView.loadUrl(Table_2);
                }
                else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ",Toast.LENGTH_SHORT).show();
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/tables/");
                }
            }
            break;
            case "Television":
                {
                    //Toast.makeText(Main3Activity.this, "in Television category"+"  "+dLength+"   "+dHeight, Toast.LENGTH_SHORT).show();
                    if(dLength<73.6&&dHeight<43.2){
                        webView.loadUrl(TV_1);
                    }
                    else if (dLength>72.4&&dLength<97.7&&dHeight>50.3&&dHeight<57.9){
                        webView.loadUrl(TV_2);
                    }
                    else if (dLength>93&&dLength<106&&dHeight>56&&dHeight<71){
                        webView.loadUrl(TV_3);
                    }
                    else if (dLength>106&&dHeight>83) {
                        webView.loadUrl(TV_4);
                    }
                    else {
                        Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ",Toast.LENGTH_SHORT).show();
                        webView.loadUrl("https://ubiquitoushomes.com/product-category/television/");
                    }
                }
            break;

        }

    }
}
