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
			doDraw("TEXT", 100, 100);
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
		 * @param text
		 * @param x
		 * @param y
		 */
		public void doDraw(String text, int x, int y) {
	        Canvas canvas = getSurfaceHolder().lockCanvas();
	        
	        // 背景の描画
	        canvas.drawColor(Color.BLACK);
	        
	        // 枠線の描画
	        drawGrid(canvas);
	        // テキストの描画
	        drawText(canvas, text, x, y);
	        
	 
	        getSurfaceHolder().unlockCanvasAndPost(canvas);
		}
		
		/**
		 * 指定した座標にテキストを描画する
		 * テキストの左下が(x,y)となる
		 * @param canvas
		 * @param text
		 * @param x
		 * @param y
		 */
		private void drawText(Canvas canvas, String text, int x, int y) {
	        Paint paint = new Paint();
	        paint.setTextSize(24);
	        paint.setColor(Color.WHITE);	        
	        canvas.drawText(text, x, y, paint);
		}
		
		private void drawGrid(Canvas canvas) {
			int width = canvas.getWidth();
			int height = canvas.getHeight();
			
			LogUtils.outputLog(String.format("canvas : width = %d, height = %d", width, height), getClass());
			
			Paint paint = new Paint();
			paint.setColor(Color.WHITE);
			
			// 縦に線を描画する
			int vCnt = width/100;
			LogUtils.outputLog(String.format("draw vertical lines. count = %d", vCnt), getClass());
			for(int i=0; i<vCnt; i++) {
				canvas.drawLine(100*i, 0, 100*i, height, paint);
			}
			
			// 横に線を描画する
			int hCnt = height/100;
			LogUtils.outputLog(String.format("draw horizontal lines. count = %d", hCnt), getClass());
			for(int i=0; i<hCnt; i++) {
				canvas.drawLine(0, 100*i, width, 100*i, paint);
			}
		}
	}
}
