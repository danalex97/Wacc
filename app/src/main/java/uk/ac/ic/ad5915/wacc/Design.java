package uk.ac.ic.ad5915.wacc;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.widget.SeekBar;

/**
 * Created by ad5915 on 25/12/15.
 */
public class Design {

    public static void changeSeekbar(SeekBar seekBarFont) {
        LinearGradient test = new LinearGradient(0.f, 0.f, 300.f, 300.f,

                new int[] { 0xFF87CEEB, 0xFF91FFB4},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setShader(test);

        seekBarFont.setProgressDrawable((Drawable) shape);
    }

}
