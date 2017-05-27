package com.be.ubihomes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Main4Activity extends ActionBarActivity implements View.OnTouchListener {
    ImageView imageView;
    ImageView product;
    Button btn;
    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);




        //Intent intent = new Intent(Main4Activity.this,Main3Activity.class);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        btn= (Button) findViewById(R.id.s);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this,Main3Activity.class);
                //startActivity(new Intent(Main4Activity.this,Main3Activity.class));
                String imgs = "R.drawable.bb";
                startActivity(intent);
            }
        });



        product=(ImageView)findViewById(R.id.product);
        product.setOnTouchListener(this);

        imageView = (ImageView) findViewById(R.id.imgView);
        imageView.setImageResource(R.drawable.bb);

        Bundle bundle = getIntent().getExtras();
        String receiver = bundle.getString("img");
        Toast.makeText(Main4Activity.this,receiver,Toast.LENGTH_SHORT).show();



    }


    float x,y=0.0f;
    boolean moving = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                moving=true;
                break;

            case MotionEvent.ACTION_MOVE :
                if (moving){
                    x=event.getRawX()-product.getWidth()/2;
                    y=event.getRawY()-product.getHeight()/2;
                    product.setX(x);
                    product.setY(y);

                }
                break;

            case MotionEvent.ACTION_UP :
                moving=false;
                break;
        }
        return true;
    }






}