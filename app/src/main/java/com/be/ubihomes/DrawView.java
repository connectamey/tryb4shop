package com.be.ubihomes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Class used to draw the points on screen. Handles user touch input.
 */
public class DrawView extends SurfaceView {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    List<Point> circlePoints;
    private Context context;
    boolean moving = false;

    private static int REFERENCE_POINT_COLOR = Color.YELLOW;
    //private static int REFERENCE_POINT_TWO_COLOR = Color.YELLOW;
    private static int MEASURE_POINT_COLOR = Color.RED;
    //private static int MEASURE_HEIGHT_COLOR = Color.GREEN;

    public DrawView(Context context){
        super(context);
        this.context = context;
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        circlePoints = new ArrayList<>();
        setWillNotDraw (false);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    protected void onDraw(Canvas canvas){
        int size = circlePoints.size();
        for(int i = 0; i < size; i++){
            //Set color based on order. First 2 points are the reference points.
            if(i < 2) paint.setColor(REFERENCE_POINT_COLOR);
            //else if(i<4) paint.setColor(REFERENCE_POINT_TWO_COLOR);
            else if(i < 4) paint.setColor(MEASURE_POINT_COLOR); // (i<4)
           // else paint.setColor(MEASURE_HEIGHT_COLOR);
            Point p = circlePoints.get(i);
            canvas.drawCircle(p.x, p.y, 10, paint);
            if(i == 1){
                canvas.drawLine(circlePoints.get(0).x, circlePoints.get(0).y, circlePoints.get(1).x, circlePoints.get(1).y, paint);
            }
            /*if(i == 3){
                canvas.drawLine(circlePoints.get(2).x, circlePoints.get(2).y, circlePoints.get(3).x, circlePoints.get(3).y, paint);
            }*/
            if(i == 3){
                canvas.drawLine(circlePoints.get(2).x, circlePoints.get(2).y, circlePoints.get(3).x, circlePoints.get(3).y, paint);
            }
            /*if(i == 5){
                canvas.drawLine(circlePoints.get(4).x, circlePoints.get(4).y, circlePoints.get(5).x, circlePoints.get(5).y, paint);
            }*/
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                moving=true;
                if(circlePoints.size() < 4) {
                    circlePoints.add(new Point(Math.round(event.getX()), Math.round(event.getY())));
                    invalidate();
                    if(circlePoints.size() == 2){
                        ((TextView) ((Activity)context).findViewById(R.id.info_lbl)).setText("select points for length");
                    }
                /*if(circlePoints.size() == 4){
                    ((TextView) ((Activity)context).findViewById(R.id.info_lbl)).setText(getResources().getString(R.string.setMeasurePoints));
                }*/
                  /*if(circlePoints.size() == 4){
                        ((TextView) ((Activity)context).findViewById(R.id.info_lbl)).setText("select points for height");
                    }*/
                   /* if(circlePoints.size() == 6){
                        ((TextView) ((Activity)context).findViewById(R.id.info_lbl)).setText("enter reference value");
                    }*/
        }
            break;
/*case MotionEvent.ACTION_MOVE:{
    if (moving){
       *//* x=event.getRawX()-product.getWidth()/2;
        y=event.getRawY()-product.getHeight()/2;
        product.setX(x);
        product.setY(y);*//*

        circlePoints.get(0).x= (int) (event.getRawX()-circlePoints.get(0).x);
        circlePoints.get(1).x= (int) (event.getRawX()-circlePoints.get(1).x);

        circlePoints.get(2).x= (int) (event.getRawX()-circlePoints.get(2).x);
        circlePoints.get(3).x= (int) (event.getRawX()-circlePoints.get(3).x);

        circlePoints.get(4).x= (int) (event.getRawX()-circlePoints.get(4).x);
        circlePoints.get(5).x= (int) (event.getRawX()-circlePoints.get(5).x);

        circlePoints.get(0).y= (int) (event.getRawY()-circlePoints.get(0).y);
        circlePoints.get(1).y= (int) (event.getRawY()-circlePoints.get(1).y);

        circlePoints.get(2).x= (int) (event.getRawX()-circlePoints.get(2).y);
        circlePoints.get(3).x= (int) (event.getRawX()-circlePoints.get(3).y);

        circlePoints.get(4).x= (int) (event.getRawX()-circlePoints.get(4).y);
        circlePoints.get(5).x= (int) (event.getRawX()-circlePoints.get(5).y);



    }
    break;
}*/


        }
        return false;
    }

    /**
     * Clears all drawn points and shapes
     */
    public void clearCanvas(){
        circlePoints.clear();
        ((TextView) ((Activity)context).findViewById(R.id.info_lbl)).setText(getResources().getString(R.string.setPicture));
        invalidate();
    }


    public double calculate(double reference, int inputUnitIndex, int outputUnitIndex){
        if(circlePoints.size() != 4){
            Toast.makeText(context, getResources().getString(R.string.error_noPoints), Toast.LENGTH_SHORT).show();
            return -1;
        }
        return Ruler.compute(circlePoints, reference, inputUnitIndex, outputUnitIndex);
    }

    /*public double calculateHeight(double reference, int inputUnitHeightIndex, int outputUnitHeightIndex){
        if(circlePoints.size() != 6){
            Toast.makeText(context, getResources().getString(R.string.error_noPoints), Toast.LENGTH_SHORT).show();
            return -1;
        }
        return Ruler.computeHeight(circlePoints, reference, inputUnitHeightIndex, outputUnitHeightIndex);
    }*/


}
