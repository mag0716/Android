package m.k.android.librarysample.volleysample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import m.k.android.librarysample.volleysample.api.WeatherApi;
import m.k.android.librarysample.volleysample.model.WeatherApiResponse;


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
        }
    }

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
}
