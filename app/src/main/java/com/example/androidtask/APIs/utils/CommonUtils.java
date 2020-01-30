package com.example.androidtask.APIs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CommonUtils {
    public static boolean isNetworkAvailable(Context mContext) {
        boolean connection = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo net_info = cm.getActiveNetworkInfo();
                if (net_info != null && net_info.isConnected()) {
                    connection = true;
                    int type = net_info.getType();
                    if (type == ConnectivityManager.TYPE_MOBILE) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }



}
