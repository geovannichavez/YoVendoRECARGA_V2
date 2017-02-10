package com.globalpaysolutions.yovendorecarga.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Josué Chávez on 10/02/2017.
 */

public final class VolleySingleton
{
    private ImageLoader mImageLoader;
    private static VolleySingleton mSingleton;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private VolleySingleton(Context pContext)
    {
        VolleySingleton.mContext = pContext;
        mRequestQueue = getRequestQueue();

        //Esta parte de Imagen Loader es para la optimizacion de la imagen
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache()
        {
            private final LruCache<String, Bitmap>  cache = new LruCache<>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized VolleySingleton getInstance(Context pContext)
    {
        if (mSingleton == null)
        {
            mSingleton = new VolleySingleton(mContext);
        }
        return mSingleton;
    }

    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public  void addToRequestQueue(Request pRequest, int pMaxRetries)
    {
        int socketTimeout = 8000;//8 segundos

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, pMaxRetries, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        pRequest.setRetryPolicy(policy);

        int retryIntents = policy.getCurrentRetryCount();
        Log.i("CurrentRetryCount", String.valueOf(retryIntents));

        getRequestQueue().add(pRequest);
    }

    public ImageLoader getImageLoader()
    {
        return mImageLoader;
    }

}
