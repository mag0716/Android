package m.k.android.sample.view.draggableviewgroup;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

public class DraggableViewGroupAdapter extends ArrayAdapter<View> {

	private List<View> mViews;
	
	/**
	 * コンストラクタ
	 * @param context
	 * @param resource
	 * @param textViewResourceId
	 * @param objects
	 */
	public DraggableViewGroupAdapter(Context context, int resource, int textViewResourceId, List<View> objects) {
		super(context, resource, textViewResourceId, objects);
		mViews = objects;
	}
	
	/**
	 * 末尾にViewを追加
	 */
	public void add(View view) {
		if(mViews != null) {
			add(view, mViews.size());
		}
	}
	
	/**
	 * 指定した位置にViewを追加
	 * @param view
	 * @param position
	 */
	public void add(View view, int position) {
		if(mViews != null && mViews.size() > position) {
			mViews.add(position, view);
			notifyDataSetChanged();
		}
	}
	
	/**
	 * 指定した位置のViewを削除
	 * @param position
	 */
	public void remove(int position) {
		if(mViews != null && mViews.size() > position) {
			mViews.remove(position);
			notifyDataSetChanged();
		}
	}
	
	public List<View> getAllViews() {
		return mViews;
	}
}
