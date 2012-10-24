package m.k.android.sample.jbsample.activityoptions;

import m.k.android.sample.jbsample.R;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

public class ActivityOptionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_options);
	}
	 
	public void onClick(View view) {
		switch(view.getId()) {
			case R.id.btn1:
				startActivityWithActivityOptions(null);
				break;
			case R.id.btn2:
				startActivityWithActivityOptions(ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getWidth(), view.getHeight()));
				break;
			case R.id.btn3:
				startActivityWithActivityOptions(ActivityOptions.makeThumbnailScaleUpAnimation(view, BitmapFactory.decodeResource(getResources(), R.drawable.screenshot), 0, 0));
				break;
			case R.id.btn4:
				startActivityWithActivityOptions(ActivityOptions.makeCustomAnimation(this, R.anim.custom_zoom_enter, R.anim.custom_zoom_exit));
				break;
		}
	}
	
	public void startActivityWithActivityOptions(ActivityOptions option) {
		final Intent intent = new Intent(this, SampleActivity.class);
		if(option != null) {
			startActivity(intent, option.toBundle());
		} else {
			startActivity(intent);
		}
	}
}
