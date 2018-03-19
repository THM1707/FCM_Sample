package com.thm.fcm_example.service;

import android.content.Context;

import com.thm.fcm_example.app.Constant;

/**
 * Created by thm on 15/03/2018.
 */
public class AppServiceClient extends ServiceClient {
    private static MyApi mMyApiInstance;
    public static MyApi getMyApiInstance(Context context){
        if (mMyApiInstance == null ){
            mMyApiInstance = createService(context, Constant.END_POINT_URL, MyApi.class);
        }
        return mMyApiInstance;
    }
}
