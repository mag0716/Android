package m.k.android.sample.homeapplication.fragment;

import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnLongClick;
import m.k.android.sample.homeapplication.R;

public class WidgetListFragment extends Fragment {

    private static final int APP_WIDGET_HOST_ID = 1;

    private static final int REQUEST_PICK_WIDGET = 1;

    @InjectView(R.id.widget_list_container)
    ViewGroup mContainer;

    private AppWidgetHost mAppWidgetHost;

    public WidgetListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_widget_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mAppWidgetHost = new AppWidgetHost(activity, APP_WIDGET_HOST_ID);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnLongClick(R.id.widget_list_container)
    public boolean showWidgetList(View view) {
        int appWidgetId = mAppWidgetHost.allocateAppWidgetId();
        final Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_PICK);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        startActivityForResult(intent, REQUEST_PICK_WIDGET);
        
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
