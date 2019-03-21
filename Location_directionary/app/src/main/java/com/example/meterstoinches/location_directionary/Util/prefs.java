package com.example.meterstoinches.location_directionary.Util;

import android.app.Activity;
import android.content.SharedPreferences;

public class prefs {
    SharedPreferences sharedPreferences;
    public prefs(Activity activity){
        sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
    }
    public  void setSearch(String search){
        sharedPreferences.edit().putString("search",search).commit();
    }
    public  String getSearch(){
        return sharedPreferences.getString("search","Batman");
    }
}
