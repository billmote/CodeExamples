package com.androidfu.codeexamples.xorstrings;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidfu.codeexamples.xorstrings.util.Base64;

public class XORStringsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(encode("my string", "arandomkey"));
        tv.setText(decode("DAtBHRAdBAUC", "arandomkey"));

    }

    public String encode(String s, String key) {
        return base64Encode(xorWithKey(s.getBytes(), key.getBytes()));
    }

    public String decode(String s, String key) {
        return new String(xorWithKey(base64Decode(s), key.getBytes()));
    }

    private byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i % key.length]);
        }
        return out;
    }

    private byte[] base64Decode(String s) {
        try {
            return Base64.decode(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String base64Encode(byte[] bytes) {
        return Base64.encodeBytes(bytes).replaceAll("\\s", "");

    }

}