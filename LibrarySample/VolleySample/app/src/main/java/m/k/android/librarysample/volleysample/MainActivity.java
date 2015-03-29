package m.k.android.librarysample.volleysample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;

import org.json.JSONObject;

import m.k.android.librarysample.volleysample.api.WeatherApi;
import m.k.android.librarysample.volleysample.model.WeatherApiResponse;

public class MainActivity extends ActionBarActivity {

    private TextView mResultText;
    private NetworkImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultText = (TextView)findViewById(R.id.result_text);
        mImage = (NetworkImageView)findViewById(R.id.image);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn1:
                executeSingleApi();
                break;

            case R.id.btn2:
                executeMultipleApi();
                break;

            case R.id.btn3:
                executeFailApi();
                break;
        }
    }

    /**
     * APIを実行する
     */
    private void executeSingleApi() {
        MyApplication application = (MyApplication)getApplication();
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                WeatherApiResponse weather = gson.fromJson(response.toString(), WeatherApiResponse.class);
                mResultText.setText(weather.toString());
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mResultText.setText("Error : error = " + error.toString());
            }
        };
        application.addToRequestQueue(
                WeatherApi.requestWeather("Japan", "Tokyo", listener, errorListener),
                "weather");
    }

    /**
     * 複数APIを実行する
     */
    private void executeMultipleApi() {
        final MyApplication application = (MyApplication)getApplication();
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                WeatherApiResponse weather = gson.fromJson(response.toString(), WeatherApiResponse.class);
                mResultText.setText(weather.toString());

                // weather API のレスポンスの値を使用して、画像を取得する
                WeatherApi.fetchWeatherIcon(application.getRequestQueue(),
                        mImage,
                        weather.getWeather().get(0).getIcon());
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mResultText.setText("Error : error = " + error.toString());
            }
        };
        application.addToRequestQueue(
                WeatherApi.requestWeather("Japan", "Tokyo", listener, errorListener),
                "weather");
    }

    /**
     * 複数APIを実行する
     * ただし、1個目の API で失敗する
     */
    private void executeFailApi() {
        final MyApplication application = (MyApplication)getApplication();
        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mResultText.setText("Error : error = " + error.toString());
            }
        };
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                WeatherApiResponse weather = gson.fromJson(response.toString(), WeatherApiResponse.class);

                if(weather != null && !weather.isError()) {
                    mResultText.setText(weather.toString());
                    // weather API のレスポンスの値を使用して、画像を取得する
                    WeatherApi.fetchWeatherIcon(application.getRequestQueue(),
                            mImage,
                            weather.getWeather().get(0).getIcon());
                } else {
                    errorListener.onErrorResponse(new VolleyError(weather != null ? weather.getMessage() :response.toString()));
                }
            }
        };
        application.addToRequestQueue(
                WeatherApi.requestWeather("Japan", "Fail", listener, errorListener),
                "weather");
    }
}
