package com.andcun.themoviedb_mvp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public class Utils {

    public static boolean isConnected(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo i = connectivityManager.getActiveNetworkInfo();
            return !(i == null || !i.isConnected() || !i.isAvailable());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
