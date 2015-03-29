package m.k.android.librarysample.volleysample;

import android.app.Application;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by kishimotomasashi on 2015/03/22.
 */
public class MyApplication extends Application {

    private RequestQueue mRequestQueue;

    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(@NonNull Request<T> request, @NonNull Object tag) {
        request.setTag(tag);
        getRequestQueue().add(request);;
    }

    public void cancelRequest(@NonNull Object tag) {
        getRequestQueue().cancelAll(tag);
    }
}
