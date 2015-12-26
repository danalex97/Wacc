package uk.ac.ic.ad5915.wacc;

/**
 * Created by ad5915 on 25/12/15.
 */
public class States {
    private static int maximum;
    private static int minimum;
    private static boolean vehicle;
    private static boolean pedestrian;

    public static void init() {
        maximum    = 100;
        minimum    = 0;
        vehicle    = false;
        pedestrian = false;
    }

    static public boolean isVehicle() {
        return vehicle;
    }

    static public boolean isPedestrian() {
        return pedestrian;
    }

    static public boolean isAuto() {
        return !isVehicle() && !isPedestrian();
    }

    static public void autoOn() {
        pedestrian = false;
        vehicle = false;
    }

    static public void pedOn() {
        autoOn();
        pedestrian = true;
    }

    static public void tubeOn() {
        autoOn();
        vehicle = true;
    }



}
