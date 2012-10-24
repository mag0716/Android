package m.k.android.sample.jbsample.notification;

import m.k.android.sample.jbsample.R;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.Notification.Style;
import android.app.PendingIntent;
import android.widget.RemoteViews;

/**
 * Notificationスタイル拡張クラス
 *
 */
public class CustomNotificationStyle extends Style {

	private String mBtnText;
	private PendingIntent mBtnPendingIntent;
	
	public CustomNotificationStyle() {
	}
	
	public CustomNotificationStyle(Builder builder) {
		setBuilder(builder);
	}
	
	@Override
	public Notification build() {
		checkBuilder();
		// mStyleをnullにしないと、mBuilder.build()でbuild()が呼び出されて、ループする。
		mBuilder.setStyle(null);
		Notification wip = mBuilder.build();
		wip.bigContentView = makeBigContentView();
		return wip;
	}
	
	public CustomNotificationStyle setButtonText(String btnText) {
		mBtnText = btnText;
		return this;
	}
	
	public CustomNotificationStyle setPendingIntetn(PendingIntent pendingIntent) {
		mBtnPendingIntent = pendingIntent;
		return this;
	}
	
	private RemoteViews makeBigContentView() {
		RemoteViews contentView = getStandardView(R.layout.notification_custom_style);
		
		contentView.setTextViewText(R.id.btn1, mBtnText);
		contentView.setOnClickPendingIntent(R.id.btn1, mBtnPendingIntent);
		
		return contentView;
	}
}
