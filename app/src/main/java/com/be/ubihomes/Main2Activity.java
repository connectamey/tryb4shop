package com.be.ubihomes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TableLayout;

import java.util.Random;

public class Main2Activity extends ActionBarActivity {


    int[] imageIds = {R.drawable.bg1,
            R.drawable.bg2,
            R.drawable.bg3,
            R.drawable.bg4,
            R.drawable.bg5,
            R.drawable.bg6,
            R.drawable.bg7,
    };

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
    }


    public void openPaint(View view) {

        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        String name = "Overalls";
        intent.putExtra("NAME", name);
        startActivity(intent);
    }

    public void openPalazzo(View view) {

        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        String name = "Palazzos";
        intent.putExtra("NAME",name);
        startActivity(intent);
    }

    public void openFridge(View view) {

        Intent intent = new Intent(this,MainActivity.class);
        String name = "Tracks";
        intent.putExtra("NAME",name);
        startActivity(intent);
    }

    public void openSofa(View view) {

        Intent intent = new Intent(this,MainActivity.class);
        String name = "Kurti";
        intent.putExtra("NAME",name);
        startActivity(intent);
    }

    public void openTable(View view) {

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
}
