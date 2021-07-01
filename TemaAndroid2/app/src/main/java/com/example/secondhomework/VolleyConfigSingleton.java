package com.example.secondhomework;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.LruCache;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleyConfigSingleton {
    private static VolleyConfigSingleton instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context context;

    private VolleyConfigSingleton(Context context)
    {
        this.context = context;
        requestQueue = getRequestQueue();
        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap> cache = new LruCache<>(20);
                    @Nullable
                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url,bitmap);
                    }
                });

    }

    public static synchronized VolleyConfigSingleton getInstance(Context context){
        if (instance == null)
        {
            instance = new VolleyConfigSingleton(context);
        }
        return instance;
    }

    public <T> void addRequest(Request<T> request){
        getRequestQueue().add(request);
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
