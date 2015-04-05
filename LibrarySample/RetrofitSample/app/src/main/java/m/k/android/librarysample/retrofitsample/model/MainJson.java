package m.k.android.librarysample.retrofitsample.model;

import lombok.Value;

/**
 * Created by kishimotomasashi on 2015/03/29.
 */
@Value
public class MainJson {
    private int humidity;
    private double pressure;
    private double temp;
    private double temp_max;
    private double temp_min;
}
