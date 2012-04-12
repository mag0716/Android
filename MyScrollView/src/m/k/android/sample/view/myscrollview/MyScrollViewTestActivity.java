package m.k.android.sample.view.myscrollview;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MyScrollViewTestActivity extends Activity {
	
	MyScrollView scrollView;
	//BothScrollView scrollView;
	LinearLayout container;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LinearLayout parent = (LinearLayout)findViewById(R.id.parent);
        
        scrollView = new MyScrollView(this);
        //scrollView = new BothScrollView(this);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        
        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        
        LayoutParams btnParams = new LayoutParams(200, LayoutParams.WRAP_CONTENT);
        for(int i=0; i<20; i++) {
        	LinearLayout layout = new LinearLayout(this);
        	for(int j=0; j<3; j++) {
        		Button btn = new Button(this);
        		btn.setText("btn" + i + "_" + j);
        		layout.addView(btn, btnParams);
        	}
    		container.addView(layout);
        }
        
        scrollView.addView(container, params);
        
        parent.addView(scrollView, params);
    }
}