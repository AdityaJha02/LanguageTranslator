package com;



import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingelton {

    Context mcontext;
    private static MySingelton Instance;
    private RequestQueue requestQueue;


    private MySingelton(Context context){
        mcontext = context;
        requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue() {
        if(requestQueue== null){
            requestQueue = Volley.newRequestQueue(mcontext.getApplicationContext());

        }

        return requestQueue;
    }
    public static synchronized MySingelton getInstance(Context context){
        if(Instance == null)
            Instance = new MySingelton(context);

        return Instance;
    }

    public void addToRequestQue(Request request){
        requestQueue.add(request);
    }

}

