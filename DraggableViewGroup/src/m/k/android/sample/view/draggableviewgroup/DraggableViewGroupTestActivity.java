package m.k.android.sample.view.draggableviewgroup;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.view.View;
import android.view.View.OnLongClickListener;

public class DraggableViewGroupTestActivity extends Activity {
	
	private DraggableViewGroup mViewGroup;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mViewGroup = (DraggableViewGroup)findViewById(R.id.draggableviewgroup);
        
        List<View> views = new ArrayList<View>();
        views.add(new DummyView(this, "View0", Color.RED, 300 ,300));
        views.add(new DummyView(this, "View1", Color.GREEN, 300 ,300));
        views.add(new DummyView(this, "View2", Color.BLUE, 300 ,300));
        views.add(new DummyView(this, "View3", Color.BLACK, 300 ,300));
        views.add(new DummyView(this, "View4", Color.CYAN, 300 ,300));
        views.add(new DummyView(this, "View5", Color.DKGRAY, 300 ,300));
        DraggableViewGroupAdapter adapter = new DraggableViewGroupAdapter(this, 0, 0, views);
        mViewGroup.setAdapter(adapter);
    }
    
    public void onClick(View view) {
    	mViewGroup.test();
    }
    
    /**
     * 
     *
     */
    public final class DummyView extends FrameLayout implements OnLongClickListener {

		public DummyView(Context context, String tag, int bgColor, int width ,int height) {
			super(context);
			
			setTag(tag);
			setBackgroundColor(bgColor);
			LayoutParams lp = new LayoutParams(width, height);
			setLayoutParams(lp);
			setOnLongClickListener(this);
		}

		@Override
		public boolean onLongClick(View v) {
			
			startDrag(null, new DragShadowBuilder(v), null, 0);
			mViewGroup.showViewLogs();
			
			return false;
		}
		
    }
}