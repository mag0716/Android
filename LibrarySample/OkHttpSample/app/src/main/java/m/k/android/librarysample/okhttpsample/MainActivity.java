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
                break;

            case R.id.btn3:
                break;
        }
    }

    private void executeSingleApi() {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?q=Tokyo,Japan")
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
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
        });
    }
}
