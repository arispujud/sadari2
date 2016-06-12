package com.example.apkit.sadari2;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by APKIT on 5/23/2016.
 */
public class SessionManager {
    public void setPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("SadariSession", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }
    public String getPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("SadariSession", Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }
}
