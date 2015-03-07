package m.k.android.librarysample.daggersample;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kishimotomasashi on 2015/03/07.
 */
@Module(injects = MainActivity.class, library = true)
public class DebuggerModule {
    @NonNull
    private final Context mContext;

    public DebuggerModule(@NonNull Context context) {
        mContext = context;
    }

    @Provides
    IDebugger provideDebugger() {
        return new ToastDebugger(mContext);
    }
}
