package m.k.android.librarysample.okhttpsample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import m.k.android.librarysample.okhttpsample.api.WeatherApi;
import m.k.android.librarysample.okhttpsample.model.WeatherApiResponse;


public class MainActivity extends ActionBarActivity {

    private TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultText = (TextView)findViewById(R.id.result_text);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn1:
                executeSingleApi();
                break;

            case R.id.btn2:
                executeMultiApi();
                break;

            case R.id.btn3:
                executeFailApi();
                break;
        }
    }

    /**
     * API を実行する
     */
    private void executeSingleApi() {
        Callback callback = new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResultText.setText("onFailure : " + e.toString());
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Gson gson = new Gson();
                final WeatherApiResponse weather = gson.fromJson(response.body().string(), WeatherApiResponse.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResultText.setText(weather.toString());
                    }
                });
            }
        };
        WeatherApi.requestWeather(((MyApplication)getApplication()).getOkHttpClient(),
                "Japan", "Tokyo", callback);
    }

    /**
     * 複数 API を実行する
     */
    private void executeMultiApi() {
        // TODO: この Callback 地獄をなんとかする。
        final Callback secondApiCallback = new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResultText.setText("onFailure(Second) : " + e.toString());
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Gson gson = new Gson();
                final WeatherApiResponse weather = gson.fromJson(response.body().string(), WeatherApiResponse.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder(mResultText.getText().toString());
                        sb.append("\n").append(weather.toString());
                        mResultText.setText(sb.toString());
                    }
                });
            }
        };

        Callback callback = new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResultText.setText("onFailure : " + e.toString());
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Gson gson = new Gson();
                final WeatherApiResponse weather = gson.fromJson(response.body().string(), WeatherApiResponse.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResultText.setText(weather.toString());
                    }
                });

                if(response.isSuccessful() && !weather.isError()) {
                    // 1回目の API に成功したら、続けて 2回目の API を実行する
                    WeatherApi.requestWeather(((MyApplication)getApplication()).getOkHttpClient(),
                            "Japan", "Osaka", secondApiCallback);
                }
            }
        };
        WeatherApi.requestWeather(((MyApplication)getApplication()).getOkHttpClient(),
                "Japan", "Tokyo", callback);
    }

    /**
     * 複数APIを実行する
     * ただし、1個目の API で失敗する
     */
    private void executeFailApi() {
        // TODO: この Callback 地獄をなんとかする。
        final Callback secondApiCallback = new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResultText.setText("onFailure(Second) : " + e.toString());
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Gson gson = new Gson();
                final WeatherApiResponse weather = gson.fromJson(response.body().string(), WeatherApiResponse.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder(mResultText.getText().toString());
                        sb.append("\n").append(weather.toString());
                        mResultText.setText(sb.toString());
                    }
                });
            }
        };

        Callback callback = new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResultText.setText("onFailure : " + e.toString());
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Gson gson = new Gson();
                final WeatherApiResponse weather = gson.fromJson(response.body().string(), WeatherApiResponse.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResultText.setText(weather.toString());
                    }
                });

                if(response.isSuccessful() && !weather.isError()) {
                    // 1回目の API に成功したら、続けて 2回目の API を実行する
                    WeatherApi.requestWeather(((MyApplication)getApplication()).getOkHttpClient(),
                            "Japan", "Osaka", secondApiCallback);
                }
            }
        };
        WeatherApi.requestWeather(((MyApplication)getApplication()).getOkHttpClient(),
                "Japan", "Fail", callback);
    }
}
