package m.k.android.librarysample.lomboksample.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by kishimotomasashi on 2015/03/08.
 */
@Data
@Accessors(prefix = "m")
public class DataSample {

    private int mNumber;
    private String mTitle;
}
