package m.k.android.librarysample.lomboksample.model;

import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by kishimotomasashi on 2015/03/08.
 */
@Value
@Accessors(prefix = "m")
public class ValueSample {

    private final int mNumber;
    private final String mTitle;
}
