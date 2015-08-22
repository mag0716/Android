package m.k.android.sample.sharedatasample;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by kishimotomasashi on 2015/08/22.
 */
public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .build());
    }
}
