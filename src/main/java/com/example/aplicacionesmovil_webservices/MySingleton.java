package com.example.aplicacionesmovil_webservices;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

public class MySingleton {

    private static MySingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    public MySingleton(Context context) {
        ctx=context;
        requestQueue=getRequestQueue();

    }

    public RequestQueue getRequestQueue() {
        if(requestQueue==null){
            Cache cache= new DiskBasedCache(ctx.getCacheDir(),1024*1024);
            Network network=new BasicNetwork(new HurlStack());
            requestQueue=new RequestQueue(cache,network);
            requestQueue= Volley.newRequestQueue(ctx.getApplicationContext());


        }
        return  requestQueue;

    }

    public static synchronized MySingleton getInstance(Context context){
        if(instance==null){
            instance=new MySingleton(context);
        }

        return instance;


    }

    public <T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }

    }
