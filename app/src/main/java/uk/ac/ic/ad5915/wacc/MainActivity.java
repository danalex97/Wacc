package uk.ac.ic.ad5915.wacc;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private void changeSeekbar(SeekBar seekBarFont) {
        LinearGradient test = new LinearGradient(0.f, 0.f, 300.f, 300.f,

                new int[] { 0xFF87CEEB, 0xFF91FFB4},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(test);

        seekBarFont.setProgressDrawable((Drawable) shape);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        changeSeekbar(seekBar1);
        SeekBar seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        changeSeekbar(seekBar2);
    }

    public void autoOnClick(View v) {

    }

    public void pedOnClick(View v) {

    }

    public void tubeOnClick(View v) {

    }
}
