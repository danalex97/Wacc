package uk.ac.ic.ad5915.wacc;

/**
 * Created by ad5915 on 26/12/15.
 */
public class PositionAnalyser {

    private final int size = 50;

    private int counter;

    private float xArray[];
    private float yArray[];
    private float zArray[];

    PositionAnalyser() {
        clearData();
    }

    public boolean addData(float x, float y, float z) {
        if ( counter >= 50 ) {
            return false;
        }
        xArray[counter] = x;
        yArray[counter] = y;
        zArray[counter] = z;
        counter ++;
        return true;
    }

    public void clearData() {
        xArray = new float[size];
        yArray = new float[size];
        zArray = new float[size];
        counter = 0;
    }

    MovementType getMovementType() {
        return MovementType.Stand;
    }
}
