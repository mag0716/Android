package m.k.android.librarysample.lomboksample.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by kishimotomasashi on 2015/03/12.
 */
public class NonNullSample {

    @Getter @Setter
    @Accessors(prefix = "m")
    @NonNull
    private String mText;

    public boolean equals(@NonNull String text) {
        return text.equals("NonNullSample");
    }

    @NonNull
    public String getData() {
        return null;
    }
}
