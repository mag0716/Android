package m.k.android.sample.homeapplication.fragment;

import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnLongClick;
import m.k.android.sample.homeapplication.R;

public class WidgetListFragment extends Fragment {

    /**
     * use for create AppWidgetHost.
     * host ID must be unique in my application.
     */
    private static final int APP_WIDGET_HOST_ID = 1;

    private static final int REQUEST_PICK_WIDGET = 1;

    @InjectView(R.id.widget_list_container)
    ViewGroup mContainer;

    private AppWidgetManager mAppWidgetManager;
    private AppWidgetHost mAppWidgetHost;

    private int mAppWidgetId;

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
        mAppWidgetManager = AppWidgetManager.getInstance(activity);
        mAppWidgetHost = new AppWidgetHost(activity, APP_WIDGET_HOST_ID);
        mAppWidgetHost.startListening();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAppWidgetHost.stopListening();
    }

    @OnLongClick(R.id.widget_list_container)
    public boolean showWidgetList(View view) {
        mAppWidgetId = mAppWidgetHost.allocateAppWidgetId();
        final Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_PICK);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        startActivityForResult(intent, REQUEST_PICK_WIDGET);

        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_PICK_WIDGET:
                if(resultCode == Activity.RESULT_OK) {
                    AppWidgetProviderInfo appWidget = mAppWidgetManager
                            .getAppWidgetInfo(mAppWidgetId);
                    addWidget(appWidget, mAppWidgetId);
                } else {
                    if(mAppWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                        mAppWidgetHost.deleteAppWidgetId(mAppWidgetId);
                        mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
                    }
                }
                break;

            default:
                break;

        }
    }

    private void addWidget(@NonNull AppWidgetProviderInfo appWidgetProviderInfo, int appWidgetId) {
        AppWidgetHostView widgetView = mAppWidgetHost.createView(getActivity(), appWidgetId, appWidgetProviderInfo);
        widgetView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        widgetView.setAppWidget(appWidgetId, appWidgetProviderInfo);
        mContainer.addView(widgetView);
    }
}
