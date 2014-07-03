package com.elegion.recyclerviewdemo;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by stepangoncarov on 03/07/14.
 */
public class ImagePaletteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_palette);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getWallpaper();
        ((ImageView) findViewById(R.id.image)).setImageDrawable(bitmapDrawable);
        Palette palette = Palette.generate(bitmapDrawable.getBitmap());
        TextView text = (TextView) findViewById(R.id.text);
        text.setTextColor(palette.getLightVibrantColor().getRgb());
        text.setBackgroundColor(palette.getMutedColor().getRgb());
    }
}
