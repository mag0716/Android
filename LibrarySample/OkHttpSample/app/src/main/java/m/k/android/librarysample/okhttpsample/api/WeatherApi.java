package m.k.android.librarysample.okhttpsample.api;

import android.support.annotation.NonNull;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by kishimotomasashi on 2015/03/26.
 */
public class WeatherApi {

    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s";

    public static void requestWeather(@NonNull OkHttpClient client,
                                      @NonNull String country,
                                      @NonNull String city,
                                      @NonNull Callback callback) {

        // GET API
        Request request = new Request.Builder()
                .url(String.format(WEATHER_API_URL, city, country))
                .get()
                .build();

        // POST API
        // Request#post 呼び出し、パラメータを渡す

        // Header の設定
        // Request#header or Request#headers or Request#addHeader

        // キャッシュ
        // OkHttpClient#setCache

        // タイムアウト
        // OkHttpClient#setConnectTimeout, OkHttpClient#setWriteTimeout, OkHttpClient#setReadTimeout

        // リトライ
        // Interceptor を実装して、OkHttpClient#interceptors().add する
        // https://github.com/square/okhttp/wiki/Interceptors


        // TODO: Interceptor サンプル

        client.newCall(request).enqueue(callback);
    }
}
