package com.example.zeus.androidgrticketsystem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Zeus on 5/2/2016.
 */
public class VolleySinglton {

    private static VolleySinglton sInstance = null;

    private RequestQueue requestQueue;

    private VolleySinglton(){
        requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySinglton getInstance(){
        if(sInstance == null){
            sInstance=new VolleySinglton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
