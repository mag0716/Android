package m.k.android.librarysample.volleysample.model;

import lombok.Value;

/**
 * Created by kishimotomasashi on 2015/03/29.
 */
@Value
public class WeatherJson {
    private String description;
    private String icon;
    private int id;
    private String main;
}
