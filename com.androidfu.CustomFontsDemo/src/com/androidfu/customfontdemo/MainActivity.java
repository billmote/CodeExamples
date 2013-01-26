package com.androidfu.customfontdemo;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;

import com.androidfu.customfontdemo.views.CustomTextView;

/**
 * MainActivity
 * 
 * <pre>
 *      20130125 -- Code Review
 * </pre>
 * 
 * @author bill.mote
 * 
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomTextView textView = (CustomTextView) findViewById(R.id.first_text_view);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/FFF_Tusj.ttf"), Typeface.NORMAL);
        textView.setText(getString(R.string.app_name));
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
