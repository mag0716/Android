package m.k.android.librarysample.volleysample.api;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONObject;

import m.k.android.librarysample.volleysample.util.ImageCache;

/**
 * Created by kishimotomasashi on 2015/03/26.
 */
public class WeatherApi {

    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s";
    private static final String WEATHER_ICON_API_URL = "http://openweathermap.org/img/w/%s.png";

    public static JsonObjectRequest requestWeather(@NonNull String country, @NonNull String city,
                                                   @NonNull Response.Listener<JSONObject> listener,
                                                   @NonNull Response.ErrorListener errorListener) {
        return new JsonObjectRequest(Request.Method.GET, String.format(WEATHER_API_URL, country, city),
                "", listener, errorListener);
    }

    public static void fetchWeatherIcon(@NonNull RequestQueue requestQueue,
                                            @NonNull NetworkImageView imageView,
                                            @NonNull String iconId) {
        imageView.setImageUrl(String.format(WEATHER_ICON_API_URL, iconId),
                new ImageLoader(requestQueue, new ImageCache()));
    }
}
