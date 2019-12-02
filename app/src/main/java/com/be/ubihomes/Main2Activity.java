package com.be.ubihomes;

import android.Manifest;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.Toast;

import com.goldducks.splashAnimations.SplashScreen;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {


    //String prod_Type;
    int[] imageIds = {R.drawable.bg1,
            R.drawable.bg2,
            R.drawable.bg3,
            R.drawable.bg4,
            R.drawable.bg5,
            R.drawable.bg6,
            R.drawable.bg7,
    };
    private final int PERMISSIONS_REQUEST_CAMERA_STORAGE = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //random background code
        TableLayout layout = (TableLayout)findViewById(R.id.activity_main2);
        Random generator = new Random();
        int randomImageId =imageIds[generator.nextInt(imageIds.length)];
        layout.setBackgroundResource(randomImageId);
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ,Manifest.permission.CAMERA},
                        PERMISSIONS_REQUEST_CAMERA_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
        SplashScreen.show(this, SplashScreen.TERMINAL_ANIMATION);
    }

    public void openPalazzo(View view) {

        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        String name = "Palazzos";
        intent.putExtra("NAME",name);
        startActivity(intent);
    }

    public void openTracks(View view) {

        Intent intent = new Intent(this,MainActivity.class);
        String name = "Tracks";
        intent.putExtra("NAME",name);
        startActivity(intent);
    }

    public void openKurti(View view) {

        Intent intent = new Intent(this,MainActivity.class);
        String name = "Kurti";
        intent.putExtra("NAME",name);
        startActivity(intent);
    }

    public void openTshirt(View view) {

        Intent intent = new Intent(this,MainActivity.class);
        String name = "TShirt";
        intent.putExtra("NAME",name);
        startActivity(intent);
    }

    public void openShoes(View view) {

        Intent intent = new Intent(this,MainActivity.class);
        String name = "Shoes";
        intent.putExtra("NAME",name);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CAMERA_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    finish();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
