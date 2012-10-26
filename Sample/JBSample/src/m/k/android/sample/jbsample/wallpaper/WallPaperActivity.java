package m.k.android.sample.jbsample.wallpaper;

import m.k.android.sample.jbsample.R;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class WallPaperActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wallpaper);
	}
	
	public void onClick(View view) {
		switch(view.getId()) {
			case R.id.btn1:
				selectWallPaper();
				break;
		}
	}
	
	private void selectWallPaper() {
		final Intent intent = new Intent();
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			intent.setAction(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
			intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this, ""));
		} else {
			intent.setAction(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
		}
		startActivity(intent);
	}
}
