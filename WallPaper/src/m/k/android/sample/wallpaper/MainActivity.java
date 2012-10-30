package m.k.android.sample.wallpaper;

import android.os.Bundle;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
    	switch(view.getId()) {
    		case R.id.btn1:
    			selectWallpaper();
    			break;
    	}
    }
    
    private void selectWallpaper() {
    	final Intent intent = new Intent();
    	intent.setAction(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
    	startActivity(intent);
    }
}
