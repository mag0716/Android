package m.k.android.librarysample.lomboksample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by kishimotomasashi on 2015/03/21.
 */
@NoArgsConstructor
public class NoArgsConstructorSample {
    @Getter
    @Accessors(prefix = "m")
    private int mNumber;

    // @NoArgsConstructor のみが指定されたクラスでは、final が指定された変数は定義不可能
    //private final String mData;
}
