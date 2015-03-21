package m.k.android.librarysample.lomboksample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by kishimotomasashi on 2015/03/21.
 */
@AllArgsConstructor
public class AllArgsConstructorSample {
    @Getter
    @Setter
    @Accessors(prefix = "m")
    private int mNumber;

    @Getter
    @Accessors(prefix = "m")
    private final String mName;

    @Getter
    @Setter
    @NonNull
    @Accessors(prefix = "m")
    private String mPassword;
}
