package com.be.ubihomes;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
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
        String name = bundle.getString("NAME");
        final String m3path = bundle.getString("PATH");
        Toast.makeText(Main3Activity.this,m3path,Toast.LENGTH_SHORT).show();
        String type_length = bundle.getString("LEN");
        String type_height = bundle.getString("HEI");

        final Double dLength = Double.parseDouble(type_length);
        final Double dHeight = Double.parseDouble(type_height);

        String Portrait_1 = getResources().getString(R.string.Potrails10);
        String Portrait_2 = getResources().getString(R.string.Potrails20);

        String Bed_1 = getResources().getString(R.string.Bed180);
        String Bed_2 = getResources().getString(R.string.Bed200);

        String Fridge_1 = getResources().getString(R.string.Refrigerator30);
        String Fridge_2 = getResources().getString(R.string.Refrigerator100);

        String Sofa_1 = getResources().getString(R.string.Sofa140);
        String Sofa_2 = getResources().getString(R.string.Sofa170);

        String Table_1 = getResources().getString(R.string.Table40);
        String Table_2 = getResources().getString(R.string.Table80);

        String TV_1 = getResources().getString(R.string.Shoes6);
        String TV_2 = getResources().getString(R.string.Shoes8);
        String TV_3 = getResources().getString(R.string.Shoes10);
        String TV_4 = getResources().getString(R.string.Shoes12);


        //final WebView webView=(WebView)findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
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
                startActivity(intent);
            }
        });


        //filtering logic
        switch (name) {

            case "Painting": {
                if (dLength < 21 && dLength > 11 && dHeight < 20 && dHeight > 11) {
                    //webView.loadUrl("https://ubiquitoushomes.com/product-category/potraits/?filter_painting-size=10-to-20-inch");
                    webView.loadUrl(Portrait_1);
                } else if (dLength < 24 && dLength > 15 && dHeight < 24 && dHeight > 15) {
                    webView.loadUrl(Portrait_2);
                } else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.Painting));
                }
            }
            break;
            case "Bed": {
                if (dLength < 200 && dLength > 183 && dHeight < 100 && dHeight > 63.5) {
                    webView.loadUrl(Bed_1);
                } else if (dLength < 218.4 && dLength > 213.4 && dHeight < 109 && dHeight > 102) {
                    webView.loadUrl(Bed_2);
                } else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.Bed));
                }

            }
            break;
            case "Fridge": {
                if (dLength < 55.5 && dLength > 30 && dHeight < 147 && dHeight > 36.1) {
                    webView.loadUrl(Fridge_1);
                } else if (dLength < 90.8 && dLength > 43 && dHeight < 179 && dHeight > 79.6) {
                    webView.loadUrl(Fridge_2);
                } else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.Fridge));
                }
            }
            break;
            case "Sofa": {
                if (dLength < 76 && dLength > 72 && dHeight < 147.8 && dHeight > 142) {
                    webView.loadUrl(Sofa_1);
                } else if (dLength < 94 && dLength > 82 && dHeight < 230 && dHeight > 152.4) {
                    webView.loadUrl(Sofa_2);
                } else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.Sofa));
                }
            }
            break;
            case "Table": {
                if (dLength < 88 && dLength > 45.72 && dHeight < 58 && dHeight > 40.64) {
                    webView.loadUrl(Table_1);
                } else if (dLength < 115 && dLength > 101.8 && dHeight < 75 && dHeight > 50.8) {
                    webView.loadUrl(Table_2);
                } else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.Table));
                }
            }
            break;
            case "Shoes": {
                if (dLength>44.79&&dLength < 73.6 && dHeight>24.89&&dHeight < 43.2) {
                    webView.loadUrl(TV_1);
                } else if (dLength > 72.4 && dLength < 97.7 && dHeight > 50.3 && dHeight < 57.9) {
                    webView.loadUrl(TV_2);
                } else if (dLength > 93 && dLength < 106 && dHeight > 56 && dHeight < 71) {
                    webView.loadUrl(TV_3);
                } else if (dLength > 106 && dHeight > 83) {
                    webView.loadUrl(TV_4);
                } else {
                    Toast.makeText(Main3Activity.this, "Sorry !!! Product not available ", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(getResources().getString(R.string.Shoes));
                }
            }
            break;

        }


    }

    public void showDownload(View view) {
        Intent i = new Intent();
        i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivity(i);
    }
}



