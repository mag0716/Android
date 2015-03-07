package m.k.android.sample.jbsample.notification;

import m.k.android.sample.jbsample.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

/**
 * 
 * JBで追加されたNotoficationサンプルActivity
 *
 */
public class NotificationActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        
        clearNotification();
    }
    
    public void onClick(View view) {
    	switch(view.getId()) {
    		case R.id.btn1:
    			notification();
    			break;
    		case R.id.btn2:
    			oldNotification();
    			break;
    		case R.id.btn3:
    			compatNotification();
    			break;
    		case R.id.btn4:
    			bigPictureStyleNotification();
    			break;
    		case R.id.btn5:
    			bigTextStyleNotification();
    			break;
    		case R.id.btn6:
    			inboxStyleNotification();
    			break;
    		case R.id.btn7:
    			styleNotification();
    			break;
    		case R.id.btn8:
    			addActionNotification();
    			break;
    		case R.id.btn9:
    			allPriorityNotification();
    			break;
    	}
    }
    
	private void notification() {
    	Notification notification = new NotificationCompat.Builder(this)
    		.setTicker("Notification Ticker")
    		.setContentTitle("Notification Title")
    		.setContentText("Notification Text")
    		.setSmallIcon(R.drawable.jellybean)
    		.setAutoCancel(true)
    		.setContentIntent(getPendingIntent())
    		.build();
    	notify(notification);
    }
    
    private void oldNotification() {
    	Notification notification = new Notification();
    	notification.flags = Notification.FLAG_AUTO_CANCEL;
    	notification.icon = R.drawable.jellybean;
    	notification.tickerText = "Notification Ticker(old)";
    	notification.setLatestEventInfo(getApplicationContext(), "Notification Title(old)", "Notification Text(old)", getPendingIntent());
    	notify(notification);
    }
    
    private void compatNotification() {
    	Notification notification = new NotificationCompat.Builder(this)
		.setTicker("Notification Ticker(Compat)")
		.setContentTitle("Notification Title(Compat)")
		.setContentText("Notification Text(Compat)")
		.setSmallIcon(R.drawable.jellybean)
		.setAutoCancel(true)
		.setContentIntent(getPendingIntent())
		.build();
    	notify(notification);
    }
    
    private void bigPictureStyleNotification() {
    	// content textは表示されない
    	Notification notification = new NotificationCompat.BigPictureStyle(
    			new NotificationCompat.Builder(this)
    			.setTicker("Notification Ticker(BigPictureStyle)")
				.setContentTitle("Notification Title(BigPictureStyle)")
				.setContentText("Notification Text(BigPictureStyle)")
				.setSmallIcon(R.drawable.jellybean)
				.setAutoCancel(true)
	    		.setContentIntent(getPendingIntent()))
    		.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.droid))
    		.setBigContentTitle("Notification Big Title(BigPictureStyle)")
    		.setSummaryText("Notification Summary Text(BigPictureStyle)")
    		.build();
    	notify(notification);
    }
    
    private void bigTextStyleNotification() {
    	Notification notification = new NotificationCompat.BigTextStyle(
    			new NotificationCompat.Builder(this)
    			.setTicker("Notification Ticker(BigTextStyle)")
				.setContentTitle("Notification Title(BigTextStyle)")
				.setContentText("Notification Text(BigTextStyle)")
				.setSmallIcon(R.drawable.jellybean)
				.setAutoCancel(true)
	    		.setContentIntent(getPendingIntent()))
    		.bigText("Notification Big Text(BigTextStyle)")
    		.setBigContentTitle("Notification Big Title(BigTextStyle)")
    		.setSummaryText("Notification Summary Text(BigTextStyle)")
    		.build();
    	notify(notification);
    }
    
    private void inboxStyleNotification() {
    	Notification notification = new NotificationCompat.InboxStyle(
    			new NotificationCompat.Builder(this)
    			.setTicker("Notification Ticker(InboxStyle)")
				.setContentTitle("Notification Title(InboxStyle)")
				.setContentText("Notification Text(InboxStyle)")
				.setSmallIcon(R.drawable.jellybean)
				.setAutoCancel(true)
	    		.setContentIntent(getPendingIntent()))
    		.addLine("addLine 1")
    		.addLine("addLine 2")
    		.addLine("addLine 3")
    		.addLine("addLine 4")
    		.addLine("addLine 5")
    		.addLine("addLine 6")
    		.addLine("addLine 7")
    		// 8行目以降は表示されず、...で表示される
    		.addLine("addLine 8")
    		.addLine("addLine 9")
    		.addLine("addLine 10")
    		.setBigContentTitle("Notification Big Title(InboxStyle)")
    		.setSummaryText("Notification Summary Text(InboxStyle)")
    		.build();
    	notify(notification);
    }
    
    @TargetApi(11)
	private void styleNotification() {
    	Notification notification = new CustomNotificationStyle(
    			new Notification.Builder(this)
    			.setTicker("Notification Ticker(CustomNotificationStyle)")
				.setContentTitle("Notification Title(CustomNotificationStyle)")
				.setContentText("Notification Text(CustomNotificationStyle)")
				.setSmallIcon(R.drawable.jellybean)
				.setAutoCancel(true)
	    		.setContentIntent(getPendingIntent()))
    		.setButtonText("Custom Button")
    		.setPendingIntetn(getPendingIntent())
    		.build();
    	notify(notification);
    }
    
    private void addActionNotification() {
    	Notification notification = new NotificationCompat.Builder(this)
    		.setTicker("Notification Ticker")
    		.setContentTitle("Notification Title")
    		.setContentText("Notification Text")
    		.setSmallIcon(R.drawable.jellybean)
    		.setAutoCancel(true)
    		.setContentIntent(getPendingIntent())
    		.addAction(android.R.drawable.ic_input_add, "Action1", getPendingIntent())
    		.addAction(android.R.drawable.ic_input_delete, "Action2", getPendingIntent())
    		.addAction(android.R.drawable.ic_input_get, "Action3", getPendingIntent())
    		// 追加できるボタンは3つまで
    		.addAction(android.R.drawable.ic_notification_clear_all, "Action4", getPendingIntent())
    		.build();
    	notify(notification);
    }
    
    private void allPriorityNotification() {
    	notify(1, new NotificationCompat.Builder(this)
    	.setContentTitle("Notification Title(PRIORITY_MIN)")
    	.setContentText("Notification Text(PRIORITY_MIN)")
    	.setPriority(NotificationCompat.PRIORITY_MIN)
    	.setSmallIcon(R.drawable.jellybean)
    	.setAutoCancel(true)
    	.build()
    	);
    	notify(2, new NotificationCompat.Builder(this)
    	.setTicker("Notification Ticker(PRIORITY_HIGH)")
    	.setContentTitle("Notification Title(PRIORITY_HIGH)")
    	.setContentText("Notification Text(PRIORITY_HIGH)")
    	.setPriority(NotificationCompat.PRIORITY_HIGH)
    	.setSmallIcon(R.drawable.jellybean)
    	.setAutoCancel(true)
    	.build()
    	);
    	notify(3, new NotificationCompat.Builder(this)
    	.setTicker("Notification Ticker(PRIORITY_LOW)")
    	.setContentTitle("Notification Title(PRIORITY_LOW)")
    	.setContentText("Notification Text(PRIORITY_LOW)")
    	.setPriority(NotificationCompat.PRIORITY_LOW)
    	.setSmallIcon(R.drawable.jellybean)
    	.setAutoCancel(true)
    	.build()
    	);
    	notify(4, new NotificationCompat.Builder(this)
    	.setTicker("Notification Ticker(PRIORITY_MAX)")
    	.setContentTitle("Notification Title(PRIORITY_MAX)")
    	.setContentText("Notification Text(PRIORITY_MAX)")
    	.setPriority(NotificationCompat.PRIORITY_MAX)
    	.setSmallIcon(R.drawable.jellybean)
    	.setAutoCancel(true)
    	.build()
    	);
    	notify(5, new NotificationCompat.Builder(this)
    	.setTicker("Notification Ticker(PRIORITY_DEFAULT)")
    	.setContentTitle("Notification Title(PRIORITY_DEFAULT)")
    	.setContentText("Notification Text(PRIORITY_DEFAULT)")
    	.setPriority(NotificationCompat.PRIORITY_DEFAULT)
    	.setSmallIcon(R.drawable.jellybean)
    	.setAutoCancel(true)
    	.build()
    	);
    }
    
    private void notify(Notification notification) {
    	notify(0, notification);
    }
    
    private void notify(int id, Notification notification) {
    	if(notification != null) {
    		NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    		manager.notify(id, notification);
    	}
    }
    
    private PendingIntent getPendingIntent() {
    	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
    	return PendingIntent.getActivity(this, 0, intent, 0);
    }
    
    private void clearNotification() {
		NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(0);
    }
}