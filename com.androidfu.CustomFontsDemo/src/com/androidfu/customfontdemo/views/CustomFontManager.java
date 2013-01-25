package com.androidfu.customfontdemo.views;

import java.util.Hashtable;

import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * CustomFontManager
 * 
 * <pre>
 *      20130124 -- Code Review
 * </pre>
 * 
 * @author bill.mote
 * 
 */
public class CustomFontManager {

    public static CustomFontManager _instance = null;
    private Hashtable<String, Typeface> _fonts = null;

    /**
     * Keep CustomFontManager() private. use getInstance() instead.
     */
    private CustomFontManager() {
        _fonts = new Hashtable<String, Typeface>();
    }

    /**
     * Get the instance of our CustomFontManager.
     * 
     * @return the _instance
     */
    public static CustomFontManager getInstance() {
        if (_instance == null) {
            _instance = new CustomFontManager();
        }
        return _instance;
    }

    /**
     * Populate our hashtable only once for each new font and/or return the Typeface to the caller. Making the
     * assumption that createFromAsset() is an expensive disk operation so we're going to hold the result in memory for
     * subsequent calls. If you have a large number of fonts in your application: a) it probably looks like a train
     * wreck, b) you need to keep an eye on memory utilization
     * 
     * @param assetManager
     * @param assetName
     * @return request
     */
    public Typeface getFont(AssetManager assetManager, String assetName) {
        Typeface typeface = null;
        if (!_fonts.containsKey(assetName)) {
            typeface = Typeface.createFromAsset(assetManager, assetName);
            _fonts.put(assetName, typeface);
        } else {
            typeface = _fonts.get(assetName);
        }
        return typeface;
    }
}
