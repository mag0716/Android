package m.k.android.librarysample.lomboksample.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by kishimotomasashi on 2015/03/21.
 */
public class ToStringSample {

    @lombok.Data
    @Accessors(prefix = "m")
    public static class Data {
        private int mNumber;
    }

    @lombok.Data
    @Accessors(prefix = "m")
    public static class DefaultConfigData extends Data {
        private String mName;
        private String mPassword;
    }

    @lombok.Data
    @Accessors(prefix = "m")
    @ToString(exclude = "mPassword")
    public static class ExcludePasswordData extends Data {
        private String mName;
        private String mPassword;
    }

    @lombok.Data
    @Accessors(prefix = "m")
    @ToString(callSuper = true, includeFieldNames = false)
    public static class CallSuperAndNotIncludeFiledNamesData extends Data {
        private String mName;
        private String mPassword;
    }
}