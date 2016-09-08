package com.smc.jobs4smcyouth;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by adao1 on 5/21/2016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
