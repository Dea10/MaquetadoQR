package com.example.maquetadoqr.Volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context contexto;

    private VolleySingleton(Context contex) {
        contexto = contex;
        mRequestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(contexto.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    public static synchronized VolleySingleton getmInstance(Context context) {
        if(mInstance == null){
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

}
