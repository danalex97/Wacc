package uk.ac.ic.ad5915.wacc;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MainService extends Service implements SensorEventListener {

    private AudioManager audioManager;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private PositionAnalyser analyser = new PositionAnalyser();
    private MovementType movementType = MovementType.Stand;
    private MovementType lastMovementType = MovementType.Stand;

    private void setVolumeByPercent(float percent) {
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int seventyVolume = (int) (maxVolume * percent);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            Log.e("Printer x: ", Float.toString(x) );
            Log.e("Printer y: ", Float.toString(y) );
            Log.e("Printer z: ", Float.toString(z) );

            if ( analyser.addData(x, y, z) == false ) {
                MovementType movementTypeNow = analyser.getMovementType();
                if (movementType != movementTypeNow) {
                    lastMovementType = movementType;
                }
                movementType = movementTypeNow;

                analyser.clearData();
                analyser.addData(x, y, z);

                if (States.isAuto()) {
                    switch (movementType) {
                        case Stand:
                            switch (lastMovementType) {
                                case Ped:
                                    setVolumeByPercent(States.getMinimumPercent());
                                    break;
                                case Tube:
                                    setVolumeByPercent(States.getMaximumPercent());
                                    break;
                            }
                            break;
                        case Ped:
                            setVolumeByPercent(States.getMaximumPercent());
                            break;
                        case Tube:
                            setVolumeByPercent(States.getMiddlePercent());
                            break;
                    }
                } else if (States.isVehicle()) {
                    switch (movementType) {
                        case Stand:
                            setVolumeByPercent(States.getMaximumPercent());
                            Log.e("Movement type:", "Stand");
                            break;
                        default:
                            setVolumeByPercent(States.getMiddlePercent());
                            Log.e("Movement type:", "Movement");
                            break;
                    }
                } else if (States.isPedestrian()) {
                    switch (movementType) {
                        case Stand:
                            setVolumeByPercent(States.getMinimumPercent());
                            Log.e("Movement type:", "Stand");
                            break;
                        default:
                            setVolumeByPercent(States.getMaximumPercent());
                            Log.e("Movement type:", "Movement");
                            break;
                    }
                }
            }
        }
    }
}
