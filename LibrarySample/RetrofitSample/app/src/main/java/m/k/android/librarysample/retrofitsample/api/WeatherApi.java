package m.k.android.librarysample.retrofitsample.api;

import android.database.Observable;
import android.support.annotation.NonNull;

import m.k.android.librarysample.retrofitsample.model.WeatherApiResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by kishimotomasashi on 2015/04/05.
 */
public class WeatherApi implements IWeatherApi {
    @Override
    public WeatherApiResponse weather(@NonNull @Query("q") String countryAndCity) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(IWeatherApi.END_POINT)
                .build();

        IWeatherApi api = restAdapter.create(IWeatherApi.class);
        return api.weather(countryAndCity);
    }

    @Override
    public void weather(@NonNull @Query("q") String countryAndCity,
                             @NonNull Callback<WeatherApiResponse> callback) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(IWeatherApi.END_POINT)
                .build();

        IWeatherApi api = restAdapter.create(IWeatherApi.class);
        api.weather(countryAndCity, callback);
    }
}
