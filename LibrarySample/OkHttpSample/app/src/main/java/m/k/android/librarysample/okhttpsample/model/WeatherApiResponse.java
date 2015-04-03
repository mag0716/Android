package m.k.android.librarysample.okhttpsample.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Value;
import m.k.android.librarysample.okhttpsample.model.CloudsJson;
import m.k.android.librarysample.okhttpsample.model.CoordJson;
import m.k.android.librarysample.okhttpsample.model.MainJson;

/**
 * Created by kishimotomasashi on 2015/03/26.
 */
@Value
public class WeatherApiResponse {
    private String base;
    private CloudsJson clouds;
    private int cod;
    private CoordJson coord;
    private long dt;
    private long id;
    private MainJson main;
    private String name;
    private SysJson sys;
    private List<WeatherJson> weather;
    private WindJson wind;

    // for error
    private String message;

    public boolean isError() {
        return cod != 200;
    }
}
