package m.k.android.librarysample.retrofitsample.api;

import android.support.annotation.NonNull;

import m.k.android.librarysample.retrofitsample.model.WeatherApiResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kishimotomasashi on 2015/04/05.
 */
public interface IWeatherApi {
    public static String END_POINT = "http://api.openweathermap.org";

    @GET("/data/2.5/weather")
    WeatherApiResponse weather(@NonNull @Query("q") String countryAndCity);

    @GET("/data/2.5/weather")
    void weather(@NonNull @Query("q") String countryAndCity,
                               @NonNull Callback<WeatherApiResponse> callback);
}
