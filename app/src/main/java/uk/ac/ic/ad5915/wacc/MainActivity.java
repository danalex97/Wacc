package uk.ac.ic.ad5915.wacc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    void initDesign() {
        SeekBar seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        Design.changeSeekbar(seekBar1);

        SeekBar seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        Design.changeSeekbar(seekBar2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDesign();
        States.init();

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
