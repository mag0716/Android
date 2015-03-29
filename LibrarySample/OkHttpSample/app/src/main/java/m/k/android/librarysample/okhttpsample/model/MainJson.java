package m.k.android.librarysample.okhttpsample.model;

import lombok.Value;

/**
 * Created by kishimotomasashi on 2015/03/29.
 */
@Value
public class MainJson {
    private int humidity;
    private int pressure;
    private double temp;
    private double temp_max;
    private double temp_min;
}
