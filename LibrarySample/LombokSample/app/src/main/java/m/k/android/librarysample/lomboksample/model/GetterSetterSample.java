package m.k.android.librarysample.lomboksample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by kishimotomasashi on 2015/03/22.
 */
public class GetterSetterSample {

    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(prefix = "m")
    public static class DefaultConfigData {
        @Getter
        @Setter
        private int mNumber;
        @Getter
        @Setter
        private String mTitle;
        @Getter
        @Setter
        private boolean mEnabled;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(prefix = "m", chain = true, fluent = true)
    public static class ChainAndFluentData {
        @Getter
        @Setter
        private int mNumber;
        @Getter
        @Setter
        private String mTitle;
        @Getter
        @Setter
        private boolean mEnabled;
    }
}
