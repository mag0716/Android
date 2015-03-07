package m.k.android.sample.utils;

import android.util.Log;

public class LogUtils {

	public final static boolean ENABLED = true;
	
	public static void outputLog(String message, Class<?> c) {
		outputLog(Log.DEBUG, c, message, null);
	}
	
	public static void outputLog(String message, Class<?> c, Exception e) {
		outputLog(Log.ERROR, c, message, e);
	}
	
	public static void outputLog(int kind, Class<?> c, String message, Exception e) {
		if(!ENABLED) {
			return;
		}
		
		String tag = c.getSimpleName();
		
		switch(kind) {
			case Log.ASSERT:
				break;
				
			case Log.DEBUG:
				Log.d(tag, message);
				break;
				
			case Log.ERROR:
				Log.e(tag, message, e);
				break;
				
			case Log.INFO:
				Log.i(tag, message);
				break;
				
			case Log.VERBOSE:
				Log.v(tag, message);
				break;
				
			case Log.WARN:
				Log.w(tag, message);
				break;
				
		}
	}
	
}
