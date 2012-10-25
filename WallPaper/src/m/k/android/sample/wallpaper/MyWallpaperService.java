package m.k.android.sample.wallpaper;

import m.k.android.sample.utils.LogUtils;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

public class MyWallpaperService extends WallpaperService {

	@Override
	public void onCreate() {
		super.onCreate();
		LogUtils.outputLog("onCreate", getClass());
	}
	
	@Override
	public Engine onCreateEngine() {
		LogUtils.outputLog("onCreateEngine", getClass());
		return new MyEngine();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LogUtils.outputLog("onConfigurationChanged", getClass());
	}
	
	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		LogUtils.outputLog("onRebind", getClass());
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		LogUtils.outputLog("onUnbind", getClass());
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		LogUtils.outputLog("onDestroy", getClass());
	}
	
	class MyEngine extends Engine {
		
		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);
			LogUtils.outputLog("onCreate", getClass());
		}
		
		@Override
		public void onSurfaceCreated(SurfaceHolder holder) {
			super.onSurfaceCreated(holder);
			LogUtils.outputLog("onSurfaceCreated", getClass());
			doDraw(0, 0);
		}
		
		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			super.onSurfaceChanged(holder, format, width, height);
			LogUtils.outputLog("onSurfaceChanged", getClass());
		}
		
		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceDestroyed(holder);
			LogUtils.outputLog("onSurfaceDestroyed", getClass());
		}
		
		@Override
		public void onDestroy() {
			super.onDestroy();
			LogUtils.outputLog("onDestroy", getClass());
		}
		
		/**
		 * 描画処理
		 * @param x
		 * @param y
		 */
		public void doDraw(int x, int y) {
	        Canvas canvas = getSurfaceHolder().lockCanvas();
	 
	        Paint paint = new Paint();
	        canvas.drawColor(Color.BLACK);
	        paint.setTextSize(24);
	        paint.setColor(Color.WHITE);
	        canvas.drawText(String.format("doDraw(%d, %d)", x, y), x, y, paint);
	 
	        getSurfaceHolder().unlockCanvasAndPost(canvas);
		}
	}
}
