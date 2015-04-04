package m.k.android.librarysample.okhttpsample;

import android.app.Application;
import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by kishimotomasashi on 2015/04/04.
 */
public class MyApplication extends Application {

    public static String TAG = "OkHttpSample";

    private OkHttpClient mClient;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public synchronized OkHttpClient getOkHttpClient() {
        if(mClient == null) {
            mClient = new OkHttpClient();
            mClient.interceptors().add(new LoggingInterceptor());
        }

        return mClient;
    }

    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            Log.d(TAG, "request = " + request.toString());

            Response response = chain.proceed(request);

            Log.d(TAG, "response = " + response.toString());

            return response;
        }
    }
}
