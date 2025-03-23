package com.example.learningprogramminglanguages;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class JsonHelper {
    public static String loadJSONFromAssets(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
