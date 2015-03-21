package m.k.android.librarysample.lomboksample.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by kishimotomasashi on 2015/03/21.
 */
public class EqualsAndHashCodeSample {
    @lombok.Data
    @Accessors(prefix = "m")
    public static class Data {
        private int mNumber;
    }

    @lombok.Data
    @Accessors(prefix = "m")
    @EqualsAndHashCode
    public static class DefaultConfigData extends Data {
        private String mName;
        private String mPassword;
    }

    @lombok.Data
    @Accessors(prefix = "m")
    @EqualsAndHashCode(exclude = "mPassword")
    public static class ExcludePasswordData extends Data {
        private String mName;
        private String mPassword;
    }

    @lombok.Data
    @Accessors(prefix = "m")
    @EqualsAndHashCode(callSuper = true)
    public static class CallSuperData extends Data {
        private String mName;
        private String mPassword;
    }
}
