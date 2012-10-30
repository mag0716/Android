package m.k.android.sample.view.draggableviewgroup;

import java.util.List;

import m.k.android.sample.utils.LogUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Drag & Drop可能なViewGroupクラス
 *
 */
public class DraggableViewGroup extends HorizontalScrollView implements OnDragListener {

	private final LayoutParams LAYOUT_PARAMS = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	
	private LinearLayout mContainer;
	private DraggableViewGroupAdapter mAdapter;
	
	/**
	 * コンストラクタ
	 * @param context
	 */
	public DraggableViewGroup(Context context) {
		super(context);
		
		mContainer = new LinearLayout(context);
		addView(mContainer, LAYOUT_PARAMS);
		setOnDragListener(this);
	}

	/**
	 * コンストラクタ
	 * @param context
	 * @param attrs
	 */
	public DraggableViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);

		mContainer = new LinearLayout(context, attrs);
		addView(mContainer, LAYOUT_PARAMS);
		setOnDragListener(this);
	}

	@Override
	public boolean onDrag(View v, DragEvent event) {
		
		switch(event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				LogUtils.outputLog("onDrag : ACTION_DRAG_STARTED : " + String.format("(%f, %f)", event.getX(), event.getY()), getClass());
				break;
				
			case DragEvent.ACTION_DRAG_LOCATION:
				
				scroll(event.getX(), event.getY());
				
				break;
				
			case DragEvent.ACTION_DROP:
				LogUtils.outputLog("onDrag : ACTION_DRAG_DROP : " + String.format("(%f, %f)", event.getX(), event.getY()), getClass());
				break;

			case DragEvent.ACTION_DRAG_ENDED:
				LogUtils.outputLog("onDrag : ACTION_DRAG_ENDED : " + String.format("(%f, %f)", event.getX(), event.getY()), getClass());
				
				test();
				
				break;
		}
		
		return true;
	}
	
	/**
	 * Adapterのセット
	 * @param adapter
	 */
	public void setAdapter(DraggableViewGroupAdapter adapter) {
		mAdapter = adapter;
		update();
	}
	
	/**
	 * Adapterの取得
	 * @return
	 */
	public DraggableViewGroupAdapter getAdapter() {
		return mAdapter;
	}
	
	/**
	 * 更新
	 */
	public void update() {
		mContainer.removeAllViews();
		List<View> views = mAdapter.getAllViews();
		if(views != null) {
			for(View v : views) {
				ViewGroup viewGroup = (ViewGroup)v.getParent();
				if(viewGroup != null) {
					viewGroup.removeView(v);
				}
				mContainer.addView(v);
			}
			requestLayout();
		}
	}
	
	public void scroll(float x, float y) {
	}
	
	public void test() {
		showViewLogs();
		View view = mAdapter.getItem(0);
		mAdapter.remove(view);
		mAdapter.add(view, 1);
		update();
		showViewLogs();
	}
	
	/**
	 * TODO: debug
	 * ログ出力する
	 */
	public void showViewLogs() {
		List<View> views = mAdapter.getAllViews();
		if(views != null) {
			for(View v : views) {
				LogUtils.outputLog(String.format("tag = %s (%f, %f)", v.getTag().toString(), v.getX(), v.getY()), getClass());
			}
		}
	}
}
