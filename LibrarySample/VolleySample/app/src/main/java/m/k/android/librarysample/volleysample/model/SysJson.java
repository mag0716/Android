package m.k.android.librarysample.volleysample.model;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Created by kishimotomasashi on 2015/03/29.
 */
@Value
public class SysJson {
    private String country;
    private int id;
    private double message;
    private long sunrise;
    private long sunset;
    private int type;
}
