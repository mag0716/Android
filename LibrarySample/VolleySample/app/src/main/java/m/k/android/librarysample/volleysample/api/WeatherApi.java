package m.k.android.librarysample.volleysample.api;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by kishimotomasashi on 2015/03/26.
 */
public class WeatherApi {

    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s";

    public static JsonObjectRequest requestWeather(@NonNull String country, @NonNull String city,
                                                   @NonNull Response.Listener<JSONObject> listener,
                                                   @NonNull Response.ErrorListener errorListener) {
        return new JsonObjectRequest(Request.Method.GET, String.format(WEATHER_API_URL, country, city),
                "", listener, errorListener);
    }
}
