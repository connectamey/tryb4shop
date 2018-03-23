package com.be.ubihomes;

import android.graphics.Point;
import android.util.Log;
import java.util.List;



/**
 * Class that handles all the mathematical operations.
 */
public class Ruler {
    private Ruler(){}

    public static double compute(List<Point> points, double scale, int inputUnitIndex, int outputUnitIndex){
        if(points.size() < 8) return -1;

        //Get reference points
        Point ref1 = points.get(0);
        Point ref2 = points.get(1);
        //Get the measurement points
        Point m1 = points.get(2);
        Point m2 = points.get(3);
        double reference = getDistance(ref1, ref2);
        Log.d("reference px ","good "+reference);
        //double reference_two = getDistance(ref3, ref4);
        double measurement = getDistance(m1, m2);
        Log.d("LengthDPI","m1-2 :"+measurement);
        measurement = (measurement * scale) / reference; //Get the actual distance
        //Convert to the right unit
        measurement = convertUnits(inputUnitIndex, reference, outputUnitIndex, measurement);
        return measurement;


    }
    public static double compute2(List<Point> points, double scale, int inputUnitIndex, int outputUnitIndex){
        if(points.size() < 4) return -1;

        //Get reference points
        Point ref1 = points.get(0);
        Point ref2 = points.get(1);
        //Get the measurement points
        Point m1 = points.get(2);
        Point m2 = points.get(3);
        double reference = getDistance(ref1, ref2);
        Log.d("reference px ","good "+reference);
        //double reference_two = getDistance(ref3, ref4);
        double measurement = getDistance(m1, m2);
        Log.d("LengthDPI","m1-2 :"+measurement);
        measurement = (measurement * scale) / reference; //Get the actual distance
        //Convert to the right unit
        measurement = convertUnits(inputUnitIndex, reference, outputUnitIndex, measurement);
        return measurement;


    }

    public static double computeHeight(List<Point> points, double scale, int inputUnitHeightIndex, int outputUnitHeightIndex){
        if(points.size() < 8) return -1;

        //Get reference points
        Point ref3 = points.get(0);
        Point ref4 = points.get(1);
        //Get the measurement points
        Point n1 = points.get(4);
        Point n2 = points.get(5);


        double reference = getDistance(ref3, ref4);
        //double reference = getDistance(ref3, ref4);
        Log.d("RefenceDPI","ref3-4 :"+reference);
        double measurement = getDistance(n1, n2);
        double height = getDistance(n1, n2);
        Log.d("HeightDPI","refn1-2 :"+height);
        height = (height * scale) / reference; //Get the actual distance
        //Convert to the right unit
        height = convertUnitsHeight(inputUnitHeightIndex, reference, outputUnitHeightIndex, height);
        Log.d("Height","measurement height :"+height);
        return height;


    }

    public static double computeWidth(List<Point> points, double scale, int inputUnitWidthIndex, int outputUnitWidthIndex){
        if(points.size() < 8) return -1;

        //Get reference points
        Point ref3 = points.get(0);
        Point ref4 = points.get(1);
        //Get the measurement points
        Point n1 = points.get(6);
        Point n2 = points.get(7);


        double reference = getDistance(ref3, ref4);
        //double reference = getDistance(ref3, ref4);
        Log.d("RefenceDPI","ref3-4 :"+reference);
        double measurement = getDistance(n1, n2);
        double width = getDistance(n1, n2);
        Log.d("WidthDPI","refn1-2 :"+width);
        width = (width * scale) / reference; //Get the actual distance
        //Convert to the right unit
        width = convertUnitsWidth(inputUnitWidthIndex, reference, outputUnitWidthIndex, width);
        Log.d("Width","measurement width :"+width);
        return width;


    }

    private static double getDistance(Point p1, Point p2){
        double x = Math.pow(p2.x - p1.x, 2);
        double y = Math.pow(p2.y - p1.y, 2);
        return Math.sqrt(x+y);
    }


    private static double convertUnits(int refUnit, double reference, int meaUnit, double measurement){
        if(refUnit == meaUnit)
            return measurement;

        measurement = toMeters(measurement, refUnit);
        switch (meaUnit){
            case 0:
                return measurement;
            case 1:
                return Utils.metersToCentimeters(measurement);
            case 2:
                return Utils.metersToMillimeters(measurement);
            case 3:
                return Utils.metersToInch(measurement);
            case 4:
                return Utils.metersToFeet(measurement);
            case 5:
                return Utils.metersToYards(measurement);
            default:
                return -1;
        }
    }


    private static double toMeters(double measurement, int refUnit){
        switch (refUnit){
            case 0:
                return measurement;
            case 1:
                return Utils.centimetersToMeters(measurement);
            case 2:
                return Utils.millimetersToMeters(measurement);
            case 3:
                return Utils.inchesToMeters(measurement);
            case 4:
                return Utils.yardsToMeters(measurement);
            default:
                return -1;
        }
    }

    private static double convertUnitsHeight(int refUnitHeight, double reference, int meaUnitHeight, double height){
        if(refUnitHeight == meaUnitHeight)
            return height;

        height = toMetersHeight(height, refUnitHeight);
        switch (meaUnitHeight){
            case 0:
                return height;
            case 1:
                return Utils.metersToCentimeters(height);
            case 2:
                return Utils.metersToMillimeters(height);
            case 3:
                return Utils.metersToInch(height);
            case 4:
                return Utils.metersToFeet(height);
            case 5:
                return Utils.metersToYards(height);
            default:
                return -1;
        }
    }




    private static double toMetersHeight(double height, int refUnitHeight){
        switch (refUnitHeight){
            case 0:
                return height;
            case 1:
                return Utils.centimetersToMeters(height);
            case 2:
                return Utils.millimetersToMeters(height);
            case 3:
                return Utils.inchesToMeters(height);
            case 4:
                return Utils.yardsToMeters(height);
            default:
                return -1;
        }
    }

    private static double convertUnitsWidth(int refUnitWidth, double reference, int meaUnitWidth, double width){
        if(refUnitWidth == meaUnitWidth)
            return width;

        width = toMetersWidth(width, refUnitWidth);
        switch (meaUnitWidth){
            case 0:
                return width;
            case 1:
                return Utils.metersToCentimeters(width);
            case 2:
                return Utils.metersToMillimeters(width);
            case 3:
                return Utils.metersToInch(width);
            case 4:
                return Utils.metersToFeet(width);
            case 5:
                return Utils.metersToYards(width);
            default:
                return -1;
        }
    }

    private static double toMetersWidth(double width, int refUnitWidth){
        switch (refUnitWidth){
            case 0:
                return width;
            case 1:
                return Utils.centimetersToMeters(width);
            case 2:
                return Utils.millimetersToMeters(width);
            case 3:
                return Utils.inchesToMeters(width);
            case 4:
                return Utils.yardsToMeters(width);
            default:
                return -1;
        }
    }






}
