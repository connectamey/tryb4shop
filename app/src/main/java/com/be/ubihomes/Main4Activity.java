package com.be.ubihomes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static android.R.attr.name;

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

//        Bundle bundle = getIntent().getExtras();
//        String path = bundle.getString("PATH");


        btn= (Button) findViewById(R.id.s);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Main4Activity.this,Main3Activity.class);
                //startActivity(new Intent(Main4Activity.this,Main3Activity.class));
                //String imgs = "R.drawable.bb";
                //startActivity(intent);
            }
        });


        String fileName = "stored_image.jpg";
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String pathDir = baseDir + "/Android/data/com.be.ubihomes/";

        String fileName2 = "stored_image.jpg";
        String baseDir2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        String pathDir2 = baseDir + "/Android/data/com.be.ubihomes/";

        product=(ImageView)findViewById(R.id.product);
        //product.setImageResource(R.drawable.tv1);
        Uri imgUri= Uri.parse("file:///sdcard/download/success.png");
        String mainu=imgUri.toString();
        Toast.makeText(getApplicationContext(),mainu,Toast.LENGTH_SHORT).show();
        product.setImageURI(imgUri);
        product.setOnTouchListener(this);

        Bundle bundle = getIntent().getExtras();
        String receiver = bundle.getString("PATH");
        Double dlen=bundle.getDouble("DLEN");
        Double dhei=bundle.getDouble("DHEI");
//        int idlen=dlen.intValue();
//        int idhei=dhei.intValue();
//        int idlen=400;
//        int idhei=400;
        product.getLayoutParams().height=400;
        product.getLayoutParams().width=400;

        Log.d("data","received "+name+" data\n"+"DLength:"+dlen+"\n"+"Height:"+dhei);


        Toast.makeText(Main4Activity.this,receiver+"\n"+dlen+"\n"+dhei,Toast.LENGTH_SHORT).show();


        Context mContext;
        imageView = (ImageView) findViewById(R.id.productBack);
        String imgUrl ="file:///sdcard/android/data/com.be.ubihomes/files/"+receiver;
        Glide.with(this).load(imgUrl)
                .thumbnail(0.5f)
                .into(imageView);
       // Uri imgUri= Uri.parse("file:///sdcard/download/success.png");

        //imageView.setImageURI(Uri.parse(path));
        /*Uri uri = Uri.parse("file:///sdcard/Android/data/com.be.ubihomes/files/"+path);

        String na = uri.toString();
        Toast.makeText(getApplicationContext(),na,Toast.LENGTH_SHORT).show();
        imageView.setImageURI(uri);*/
//        imageView.setImageResource(Integer.parseInt("file:///sdcard/Android/data/com.be.ubihomes/files/"+path));




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
