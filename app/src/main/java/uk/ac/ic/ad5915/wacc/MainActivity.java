package uk.ac.ic.ad5915.wacc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private static final int stateId1 = 1;
    private static final int stateId2 = 2;

    void initDesign() {
        SeekBar seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        Design.changeSeekbar(seekBar1);

        SeekBar seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        Design.changeSeekbar(seekBar2);
    }

    void setListner(SeekBar seekBar, final int stateId) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            public void onStopTrackingTouch(SeekBar bar)
            {
            }

            public void onStartTrackingTouch(SeekBar bar)
            {
            }

            @Override
            public void onProgressChanged(SeekBar bar, int paramInt, boolean paramBoolean)
            {
                Log.e("Seekbar change:", Integer.toString(paramInt) );
                int percent = paramInt * 10;
                switch ( stateId ) {
                    case stateId1:
                        States.setMaximum(percent);
                        break;
                    case stateId2:
                        States.setMinimum(percent);
                        break;
                }
            }
        });
    }

    void setListners() {
        SeekBar seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        setListner(seekBar1, stateId1);


        SeekBar seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        setListner(seekBar2, stateId2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDesign();
        States.init();
        setListners();

        startService(new Intent(this, MainService.class));
    }

    public void autoOnClick(View v) {
        States.autoOn();
    }

    public void pedOnClick(View v) {
        States.pedOn();
    }

    public void tubeOnClick(View v) {
        States.tubeOn();
    }
}
