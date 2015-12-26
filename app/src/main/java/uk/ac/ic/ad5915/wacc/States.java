package uk.ac.ic.ad5915.wacc;

/**
 * Created by ad5915 on 25/12/15.
 */
public class States {
    private static int totalMaximum;
    private static int maximum;
    private static int minimum;

    private static boolean vehicle;
    private static boolean pedestrian;

    public static void init() {
        maximum      = 100;
        totalMaximum = 100;

        minimum      = 0;

        vehicle    = false;
        pedestrian = false;
    }

    static public void setMaximum(int value) {
        maximum = value;
    }

    static public void setMinimum(int value) {
        minimum = value;
    }

    static public float getMaximumPercent() {
        return (float)(maximum) / (float)(totalMaximum);
    }

    static public float getMinimumPercent() {
        return (float)(minimum) / (float)(totalMaximum);
    }

    static public float getMiddlePercent() {
        return (float)(minimum + maximum) / (float)(2 * totalMaximum);
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
