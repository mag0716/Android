package m.k.android.librarysample.retrofitsample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import m.k.android.librarysample.retrofitsample.api.WeatherApi;
import m.k.android.librarysample.retrofitsample.model.WeatherApiResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView)findViewById(R.id.result_text);
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

    private void executeSingleApi() {
        WeatherApi api = new WeatherApi();
        api.weather("Tokyo,Japan", new Callback<WeatherApiResponse>() {
            @Override
            public void success(WeatherApiResponse weatherApiResponse, Response response) {
                mText.setText(weatherApiResponse.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                mText.setText("failure : error = " + error.toString());
            }
        });
    }

    private void executeMultiApi() {
        final WeatherApi api = new WeatherApi();
        api.weather("Tokyo,Japan", new Callback<WeatherApiResponse>() {
            @Override
            public void success(WeatherApiResponse weatherApiResponse, Response response) {
                mText.setText(weatherApiResponse.toString());

                if(!weatherApiResponse.isError()) {
                    api.weather("Osaka,Japan", new Callback<WeatherApiResponse>() {
                        @Override
                        public void success(WeatherApiResponse weatherApiResponse, Response response) {
                            StringBuilder sb = new StringBuilder(mText.getText());
                            sb.append("\n").append(weatherApiResponse.toString());
                            mText.setText(sb.toString());
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            mText.setText("failure : error = " + error.toString());
                        }
                    });
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mText.setText("failure : error = " + error.toString());
            }
        });
    }

    private void executeFailApi() {
        final WeatherApi api = new WeatherApi();
        api.weather("Dummy,Japan", new Callback<WeatherApiResponse>() {
            @Override
            public void success(WeatherApiResponse weatherApiResponse, Response response) {
                mText.setText(weatherApiResponse.toString());

                if(!weatherApiResponse.isError()) {
                    api.weather("Osaka,Japan", new Callback<WeatherApiResponse>() {
                        @Override
                        public void success(WeatherApiResponse weatherApiResponse, Response response) {
                            StringBuilder sb = new StringBuilder(mText.getText());
                            sb.append("\n").append(weatherApiResponse.toString());
                            mText.setText(sb.toString());
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            mText.setText("failure : error = " + error.toString());
                        }
                    });
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mText.setText("failure : error = " + error.toString());
            }
        });
    }
}
