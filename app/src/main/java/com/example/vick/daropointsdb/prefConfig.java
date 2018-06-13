package com.example.vick.daropointsdb;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.vick.daropointsdb.R;


public class prefConfig {

    private SharedPreferences sharedPref;
    private Context context;

    public prefConfig(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);
    }

    public void writeLoginstatus (boolean status){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        return sharedPref.getBoolean(context.getString(R.string.pref_login_status),false);
    }

    public void writeUsername(String username){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.pref_username),username);
        editor.commit();
    }

    public String readUsername(){

        return sharedPref.getString(context.getString(R.string.pref_username),"user");
    }

    public void displayToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
