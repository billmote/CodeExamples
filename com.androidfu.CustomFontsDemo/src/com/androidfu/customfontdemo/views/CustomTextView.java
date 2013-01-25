package com.androidfu.customfontdemo.views;

import com.androidfu.customfontdemo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * StyledTextView
 * 
 * <pre>
 *      20130124 -- Code Review
 * </pre>
 * 
 * @author bill.mote
 * 
 */
public class CustomTextView extends TextView {

    private String _customFont = null;
    private int _style;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomAttributes(attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomAttributes(attrs);
    }

    /**
     * Set custom attributes, particularly we're setting our font
     * 
     * @param attrs
     */
    private void setCustomAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        _customFont = a.getString(R.styleable.CustomTextView_font);
        if (_customFont != null) {
            if (!isInEditMode()) {
                CustomFontManager fontManager = CustomFontManager.getInstance();
                super.setTypeface(fontManager.getFont(getContext().getAssets(), _customFont), _style);
            }
        }
        a.recycle();
    }

    /**
     * If the user set a style assign it to our _style field so it can be applied to our custom font. Calling super
     * here allows you to use CustomTextView even if you're not changing the font.
     */
    public void setTypeface(Typeface tf, int style) {
        this._style = style;
        super.setTypeface(tf, style);
        return;
    }
}
