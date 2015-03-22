package m.k.android.librarysample.lomboksample.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by kishimotomasashi on 2015/03/21.
 */
@RequiredArgsConstructor
public class RequiredArgsConstructorSample {
    @Getter
    @Setter
    @Accessors(prefix = "m")
    private int mNumber;

    @Getter
    @Accessors(prefix = "m")
    private final String mName;

    // 下記の様に宣言すると、コンストラクタ内で mPassword に対して、null チェックが行われる。
    // setter は正常に引数に対して、null チェックが行われている。
//    @Getter
//    @Setter
//    @NonNull
//    @Accessors(prefix = "m")
//    private String mPassword;

    @Getter
    @Setter
    @NonNull
    private String password;
}
