package com.be.ubihomes;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements InputDialog.InputDialogListener{

    private DrawView drawView;
    private DrawView2 drawView2;
    private FrameLayout preview;
    private Button btn_ok;
    private Button btn_cancel;
    private Button btn_takePicture;
    private File photoFile;
    private double result;
    public String name;
    private double resultHeight;
    private double resultWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
        name = bundle.getString("NAME");
            Toast.makeText(MainActivity.this,"You have selected"+" "+name+" "+"Category",Toast.LENGTH_SHORT).show();
            Log.d("data","received "+name+" data");
        }


        preview = (FrameLayout) findViewById(R.id.camera_preview);



        btn_takePicture = (Button) findViewById(R.id.button_takePicture);
        btn_ok = (Button) findViewById(R.id.button_calculate);
        btn_cancel = (Button) findViewById(R.id.button_cancel);

        addPictureButton();

        btn_takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InputDialog().show(getFragmentManager(), "input_dialog");
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.contains("TShirt") || name.contains("Kurti")){
                drawView.clearCanvas();
            }
            else{
                    drawView2.clearCanvas();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
//            case R.id.action_settings:
//                break;
            case R.id.action_cleanStorage:
                cleanPhotoStorage();
                break;
            case R.id.action_choosePhoto:
                dispatchChoosePhotoIntent();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            photoFile = null;
            try {
                String imgp;
               photoFile = createImageFile();
                imgp=photoFile.getName();


            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, "Error creating image", Toast.LENGTH_SHORT);
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void addPictureButton(){
        preview.removeAllViews();

        btn_cancel.setVisibility(View.GONE);
        btn_ok.setVisibility(View.GONE);
        btn_takePicture.setVisibility(View.VISIBLE);
    }

    private void pictureTaken(){
        preview.removeAllViews();

        btn_cancel.setVisibility(View.VISIBLE);
        btn_ok.setVisibility(View.VISIBLE);
        btn_takePicture.setVisibility(View.GONE);

        ImageSurface image = new ImageSurface(this, photoFile);
        preview.addView(image);

        ((TextView) findViewById(R.id.info_lbl)).setText(getResources().getString(R.string.setReferencePoints));
    if (name.contains("TShirt") || name.contains("Kurti"))
    {
        drawView = new DrawView(this);
        preview.addView(drawView);
    }
    else{
        drawView2 = new DrawView2(this);
        preview.addView(drawView2);
    }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_SELECT_PHOTO = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case REQUEST_IMAGE_CAPTURE:
                if(resultCode == RESULT_OK)
                        pictureTaken();
                break;
            case REQUEST_SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    String filePath = Utils.getPath(this, uri);
                    photoFile = new File(filePath);
                    pictureTaken();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "taken";
        File storageDir = getExternalFilesDir(null);

        return File.createTempFile(
                imageFileName,  /* prefix */
                ".bmp",         /* suffix */
                storageDir      /* directory */
        );
    }

    private void cleanPhotoStorage(){
        File storageDir = getExternalFilesDir(null);
        File fList[] = storageDir.listFiles();
        //Search for pictures in the directory
        for(int i = 0; i < fList.length; i++){
            String pes = fList[i].getName();
            if(pes.endsWith(".jpg"))
                new File(fList[i].getAbsolutePath()).delete();
        }
        Toast.makeText(this, getResources().getString(R.string.storageDeleted), Toast.LENGTH_SHORT).show();
    }

    private void dispatchChoosePhotoIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.action_choosePhoto)), REQUEST_SELECT_PHOTO);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        int inputUnit = ((Spinner)dialog.getDialog().findViewById(R.id.input_unit_chooser)).getSelectedItemPosition();
       int inputUnitHeight = ((Spinner)dialog.getDialog().findViewById(R.id.input_unit_chooser)).getSelectedItemPosition();
        int outputUnit = ((Spinner) dialog.getDialog().findViewById(R.id.output_unit_chooser)).getSelectedItemPosition();
        try {
            double reference = Double.parseDouble(((EditText) dialog.getDialog().findViewById(R.id.reference_input)).getText().toString());
            //result = drawView.calculate(reference, inputUnit, outputUnit);
            //resultHeight = drawView.calculateHeight(reference, inputUnit, outputUnit);
            //resultWidth = drawView.calculateWidth(reference, inputUnit, outputUnit);
            if (name.contains("TShirt") || name.contains("Kurti")){
                result = drawView.calculate(reference, inputUnit, outputUnit);
                resultHeight = drawView.calculateHeight(reference, inputUnit, outputUnit);
                resultWidth = drawView.calculateWidth(reference, inputUnit, outputUnit);
                showResult();
            }
            else{
                result = drawView2.calculate2(reference, inputUnit, outputUnit);
                showResult();

            }

        }catch(NumberFormatException ex){
            Toast.makeText(this, getResources().getString(R.string.error_numberFormat), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //Do absolutely nothing
    }

    private void showResult(){
        if(result != -1) {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.result_lbl) + decimalFormat.format(result)+"\n" + "Height :"+decimalFormat.format(resultHeight)+"\n" + "Width :"+decimalFormat.format(resultWidth));


            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    Bundle bundle = getIntent().getExtras();
                    String name = bundle.getString("NAME");
                    String fpath=photoFile.getName();
                    intent.putExtra("PATH",fpath);
                    intent.putExtra("NAME",name);
                    intent.putExtra("LEN",decimalFormat.format(result));
                    intent.putExtra("HEI",decimalFormat.format(resultHeight));
                    intent.putExtra("WID",decimalFormat.format(resultWidth));
                    Toast.makeText(MainActivity.this,"Category :"+" "+name+"\n\n"+"Length : "+" "+decimalFormat.format(result)+""+"\nHeight : "+" "+decimalFormat.format(resultHeight)+""+"\nWidth : "+" "+decimalFormat.format(resultWidth),Toast.LENGTH_SHORT).show(); //
                    Log.d("data","received "+name+" data\n"+"Length:"+decimalFormat.format(result)+"\n Height:"+decimalFormat.format(resultHeight)+"\n Width:"+decimalFormat.format(resultWidth));
                    startActivity(intent);
                }
            });
            builder.create().show();
        }
    }

    /*public void Go_to_Website(View view) {
        Intent intent=new Intent(MainActivity.this,Main3Activity.class);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("NAME");
        intent.putExtra("NAME",name);
       startActivity(intent);


    }*/
}