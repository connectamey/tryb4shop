package com.be.ubihomes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Switch;
import android.widget.Toast;

public class Main3Activity extends ActionBarActivity {
    WebView webView;
    //Double dLength,dHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        webView = (WebView) findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("https://ubiquitoushomes.com/");

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("NAME");
        String type_length = bundle.getString("LEN");
        String type_height = bundle.getString("HEI");
        //Toast.makeText(Main3Activity.this, "Category: "+name+"\n Length: "+type_length+"\n Height: "+type_height,Toast.LENGTH_SHORT).show();

        Double dLength = Double.parseDouble(type_length);
        Double dHeight = Double.parseDouble(type_height);


        //filtering logic
        switch(name) {

            case "Painting":
            {
                if(dLength<21&&dLength>11&&dHeight<20&&dHeight>11){
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/potraits/?filter_painting-size=10-to-20-inch");
                }
                else if(dLength<24&&dLength>15&&dHeight<24&&dHeight>15) {
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/potraits/?filter_painting-size=20-to-30-inch");
                }
            }
           break;
            case "Bed":
            {
                if(dLength<200&&dLength>183&&dHeight<100&&dHeight>63.5){
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/bed/?filter_bed-size=180-to-200-cm");
                }
                else if(dLength<218.4&&dLength>213.4&&dHeight<109&&dHeight>102){
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/bed/?filter_bed-size=200-to-220-cm");
                }
            }
            break;
            case "Fridge":
            {
                if(dLength<55.5&&dLength>30&&dHeight<147&&dHeight>36.1){
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/refrigerator/?filter_refrigerator=height-30-to-100-cm");
                }
                else if(dLength<90.8&&dLength>43&&dHeight<179&&dHeight>79.6){
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/refrigerator/?filter_refrigerator=height-100-to-200-cm");
                }
            }
            break;
            case "Sofa":
            {
                if(dLength<76&&dLength>72&&dHeight<147.8&&dHeight>142){
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/sofa/?filter_sofa-size=140-to-170-cm");
                }
                else if(dLength<94&&dLength>82&&dHeight<230&&dHeight>152.4){
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/sofa/?filter_sofa-size=170-to-230-cm");
                }
            }
            break;
            case "Table":
            {
                if(dLength<88&&dLength>45.72&&dHeight<58&&dHeight>40.64){
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/tables/?filter_table-size=40-to-80-cm");
                }
                else if(dLength<115&&dLength>101.8&&dHeight<75&&dHeight>50.8){
                    webView.loadUrl("https://ubiquitoushomes.com/product-category/tables/?filter_table-size=80-to-120-cm");
                }
            }
            break;
            case "Television":
                {
                    //Toast.makeText(Main3Activity.this, "in Television category"+"  "+dLength+"   "+dHeight, Toast.LENGTH_SHORT).show();
                    if(dLength<73.6&&dHeight<43.2){
                        webView.loadUrl("https://ubiquitoushomes.com/product-category/television/?filter_screen=20-to-30");
                    }
                    else if (dLength>72.4&&dLength<97.7&&dHeight>50.3&&dHeight<57.9){
                        webView.loadUrl("https://ubiquitoushomes.com/product-category/television/?filter_screen=30-to-40");
                    }
                    else if (dLength>93&&dLength<106&&dHeight>56&&dHeight<71){
                        webView.loadUrl("https://ubiquitoushomes.com/product-category/television/?filter_screen=40-to-50");
                    }
                    else if (dLength>106&&dHeight>83) {
                        webView.loadUrl("https://ubiquitoushomes.com/product-category/television/?filter_screen=50-to-60");
                    }
                }
            break;

        }

    }
}
