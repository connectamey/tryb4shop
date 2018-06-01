package com.be.ubihomes;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Main3Activity extends ActionBarActivity {

    WebView webView;
    ProgressBar progressBar;
    Button btn;
    private long enqueue;
    private DownloadManager dm;
    public String buyurl;
    private ArrayList<String> searchHistory;

    //String fName = "chamya.png";

    //back button in webview


   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && searchHistoryPos > 0) {
            SyncStateContract.Constants.LogMessage("Handling back keyevent");
            //remove eldest entry
            searchHistory.remove(mUrl);
            //make the url-to-load be the latest entry after deletion
            mUrl = searchHistory.get(searchHistory.size);
            //load the new url
            mWebView.loadDataWithBaseURL(mUrl...);
        }
    }*/



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        searchHistory = new ArrayList<String>();

        final WebView webView = (WebView) findViewById(R.id.webView1);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());


        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("https://ubiquitoushomes.com/");

        Bundle bundle = getIntent().getExtras();
        String name = "";
        name = bundle.getString("NAME");
        final String m3path = bundle.getString("PATH");
        Toast.makeText(Main3Activity.this,m3path,Toast.LENGTH_SHORT).show();
        String type_length = bundle.getString("LEN");
        String type_height = bundle.getString("HEI");
        String type_width = bundle.getString("WID");


        final Double dLength = Double.parseDouble(type_length);
        final Double dHeight = Double.parseDouble(type_height);
        final Double dWidth = Double.parseDouble(type_width);

        String Palazzo_30 = getResources().getString(R.string.Palazzo30);
        String Palazzo_40 = getResources().getString(R.string.Palazzo40);
        String Palazzo_50 = getResources().getString(R.string.Palazzo50);

        String Tracks_1 = getResources().getString(R.string.Tracks30);
        String Tracks_2 = getResources().getString(R.string.Tracks70);
        String Tracks_3 = getResources().getString(R.string.Tracks90);

        String Kurti_1 = getResources().getString(R.string.Kurti1);
        String Kurtilong_1 = getResources().getString(R.string.Kurtilong1);
        String Kurti_2 = getResources().getString(R.string.Kurti2);
        String Kurtilong_2 = getResources().getString(R.string.Kurtilong2);
        String Kurti_3 = getResources().getString(R.string.Kurti3);
        String Kurtilong_3 = getResources().getString(R.string.Kurtilong3);

        String TShirt_1 = getResources().getString(R.string.TShirt38);
        String Tshirt_1full = getResources().getString(R.string.TShirt38full);
        String TShirt_2 = getResources().getString(R.string.TShirt40);
        String Tshirt_2full = getResources().getString(R.string.TShirt40full);
        String TShirt_3 = getResources().getString(R.string.TShirt42);
        String TShirt_3full = getResources().getString(R.string.TShirt42full);
        String TShirt_4 = getResources().getString(R.string.TShirt44);
        String TShirt_4full = getResources().getString(R.string.TShirt44full);
        String Tshirt = getResources().getString(R.string.TShirt);

        String Shoes_1 = getResources().getString(R.string.Shoes4);
        String Shoes_2 = getResources().getString(R.string.Shoes6);
        String Shoes_3 = getResources().getString(R.string.Shoes8);
        String Shoes_4 = getResources().getString(R.string.Shoes10);

        //final WebView webView=(WebView)findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

               if (webView.getUrl().contains("buy")) {
                  buyurl=webView.getUrl();
                   Log.e("buyurl is", "this " + buyurl);
               }

                progressBar.setVisibility(View.INVISIBLE);
                searchHistory.add(url);
                //webView.loadUrl(url);
               // Toast.makeText(Main3Activity.this, url, Toast.LENGTH_SHORT).show();
                if(webView.getUrl().contains(".png")) {
                    DownloadManager.Request request = new DownloadManager.Request(
                            Uri.parse(url));
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "success.png");
// You can change the name of the downloads, by changing "download" to everything you want, such as the mWebview title...
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    File folder1 = new File("/sdcard/download/success.png");
                    boolean delete=folder1.delete();
                    Toast.makeText(getApplicationContext(),"wow "+delete,Toast.LENGTH_SHORT).show();

                    if (folder1.exists()){


                    }else {
                        dm.enqueue(request);

                        Toast.makeText(Main3Activity.this, "Downloading File", //To notify the Client that the file is being downloaded
                                Toast.LENGTH_LONG).show();

                        //startActivity(new Intent(Main3Activity.this,Main4Activity.class));
                    }

                } else {

                }

            }
        });


        progressBar = ((ProgressBar) findViewById(R.id.progress_loader));


        btn = (Button) findViewById(R.id.s);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                //Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                intent.putExtra("PATH",m3path);
                intent.putExtra("DLEN",dLength);
                intent.putExtra("DHEI",dHeight);
                intent.putExtra("BUYU",buyurl);
                startActivity(intent);
            }
        });


        //filtering logic
        switch (name) {

            case "Palazzos": {
                if (dLength > 0 && dLength <= 76.2) {
                    webView.loadUrl(Palazzo_30);
                } else if (dLength > 76.2 && dLength <= 101.6 ) {
                    webView.loadUrl(Palazzo_40);
                } else if(dLength > 101.6) {
                    webView.loadUrl(Palazzo_50);

                } else {
                    Toast.makeText(Main3Activity.this, "Check out from our collection of Palazzos ", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.Palazzo));
                }

            }
            break;
            case "Tracks": {
                if (dLength > 0 && dLength <= 76) {
                    webView.loadUrl(Tracks_1);
                } else if (dLength > 76 && dLength <= 81) {
                    webView.loadUrl(Tracks_2);
                }
                else if(dLength > 81){
                    webView.loadUrl(Tracks_3);
                }
                else {
                    Toast.makeText(Main3Activity.this, "Check out from our collection of Tracks ", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.Tracks));
                }
            }
            break;
            case "Kurti": {
                if (dLength > 0 && dLength <= 35) {
                    if(dHeight > 0 && dHeight <= 65) {
                        webView.loadUrl(Kurti_1); //Short Sleeve Kurti
                    }
                    else if(dHeight > 65){
                        webView.loadUrl(Kurtilong_1); //Long Sleeve Kurti
                    }
                    else{
                        webView.loadUrl(Kurti_1);
                    }
                } else if (dLength > 35 && dLength <= 37) {
                    if (dHeight > 0 && dHeight <= 70) {
                        webView.loadUrl(Kurti_2); //Short Sleeve Kurti
                    } else if (dHeight > 70) {
                        webView.loadUrl(Kurtilong_2); //Long Sleeve Kurti

                    } else {
                        webView.loadUrl(Kurti_2);
                    }
                }else if (dLength > 37 ) {
                        if(dHeight > 0 && dHeight <= 75) {
                            webView.loadUrl(Kurti_3); //Short Sleeve Kurti
                        }
                        else if(dHeight > 75){
                            webView.loadUrl(Kurtilong_3); //Long Sleeve Kurti
                        }
                        else{
                            webView.loadUrl(Kurti_3);
                        }
                } else {
                    Toast.makeText(Main3Activity.this, "Check out from our collection of Kurtis", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.Kurti));
                }
            }
            break;
            case "TShirt": {
                if (dLength > 0 && dLength <= 35) {
                    if(dHeight > 0 && dHeight <= 13) {
                        webView.loadUrl(TShirt_1); //Short sleeve tshirt
                    }
                    else if(dHeight > 13){
                        webView.loadUrl(Tshirt_1full); //Long Sleeve tshirt
                    }
                    else{
                        webView.loadUrl(Tshirt);
                    }
                } else if (dLength > 35 && dLength <= 37) {
                    if(dHeight > 0 && dHeight <= 17) {
                        webView.loadUrl(TShirt_2);
                    }
                    else if (dHeight > 17){
                        webView.loadUrl(Tshirt_2full);
                    }
                    else{
                        webView.loadUrl(Tshirt);
                    }
                }else if(dLength > 37 && dLength <= 39){
                    if(dHeight > 0 && dHeight <= 25) {
                        webView.loadUrl(TShirt_3);
                    }
                    else if (dHeight > 25){
                        webView.loadUrl(TShirt_3full);
                    }
                    else{
                        webView.loadUrl(Tshirt);
                    }
                }else if(dLength > 39){
                    if(dHeight > 0 && dHeight <= 25) {
                        webView.loadUrl(TShirt_4);
                    }
                    else if(dHeight > 25){
                        webView.loadUrl(TShirt_4full);
                    }
                    else{
                        webView.loadUrl(Tshirt);
                    }
                }else {
                    Toast.makeText(Main3Activity.this, "Check out from our collection of Tshirts", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.TShirt));
                }
            }
            break;
            case "Shoes": {
                if (dLength <= 22) {
                    webView.loadUrl(Shoes_1);
                } else if (dLength > 22 && dLength < 24) {
                    webView.loadUrl(Shoes_2);
                } else if (dLength > 24 && dLength < 26) {
                    webView.loadUrl(Shoes_3);
                } else if (dLength > 26) {
                    webView.loadUrl(Shoes_4);
                } else {
                    Toast.makeText(Main3Activity.this, "Check out from our collection of Shoes", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.Shoes));
                }
            }
            break;
            default:{
                Toast.makeText(Main3Activity.this, "Check out from our collection of Shoes", Toast.LENGTH_SHORT).show();
                webView.loadUrl(getResources().getString(R.string.Shoes));
            }
        }

       }

    public void showDownload(View view) {
        Intent i = new Intent();
        i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivity(i);
    }
}



