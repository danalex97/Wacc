package uk.ac.ic.ad5915.wacc;

import android.util.Log;

/**
 * Created by ad5915 on 26/12/15.
 */
public class PositionAnalyser {

    private final int size = 10;
    private final float standConstant = 5;
    private final float standxConstant = 3/2;

    private int counter;

    private float xArray[];
    private float yArray[];
    private float zArray[];

    PositionAnalyser() {
        clearData();
    }

    public boolean addData(float x, float y, float z) {
        if ( counter >= size ) {
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

        float xAmplitude = 0;
        float yAmplitude = 0;
        float zAmplitude = 0;

        for (int i = 0; i < size - 1; ++i) {
            xAmplitude += Math.abs( xArray[i+1] - xArray[i] );
            yAmplitude += Math.abs( yArray[i+1] - yArray[i] );
            zAmplitude += Math.abs( zArray[i+1] - zArray[i] );
        }

        Log.e("Amplitude on X:", Float.toString(xAmplitude));
        Log.e("Amplitude on Y:", Float.toString(yAmplitude));
        Log.e("Amplitude on Z:", Float.toString(zAmplitude));

        float tAmplitude = xAmplitude + yAmplitude + zAmplitude;
        if ( tAmplitude > standConstant ) {
            if ( xAmplitude < standxConstant
                    || yAmplitude < standxConstant
                    || zAmplitude < standxConstant ) {
                return MovementType.Tube;
            } else {
                return MovementType.Ped;
            }
        }
        return MovementType.Stand;
    }
}
