package m.k.android.sample.homeapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import m.k.android.sample.homeapplication.R;

public class ApplicationListFragment extends Fragment {

    @InjectView(R.id.grid)
    GridView mGrid;
    Adapter mAdapter;

    private PackageManager mPackageManager;

    private List<ResolveInfo> mApplicationInfoList;

    public ApplicationListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_application_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mPackageManager = activity.getPackageManager();
        mApplicationInfoList = getApplicationList(mPackageManager);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new Adapter(getActivity(), mPackageManager, R.layout.application_list_item);
        mAdapter.addAll(mApplicationInfoList);
        mGrid.setAdapter(mAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnItemClick(R.id.grid)
    void launchApplication(int position) {
        final ResolveInfo info = mAdapter.getItem(position);
        final Intent intent = mPackageManager.getLaunchIntentForPackage(info.activityInfo.packageName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        startActivity(intent);
    }

    private List<ResolveInfo> getApplicationList(@NonNull PackageManager manager) {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // see PackageManager.MATCH_DEFAULT_ONLY
        return manager.queryIntentActivities(intent, 0);
    }

    public static class Adapter extends ArrayAdapter<ResolveInfo> {

        static class ViewHolder {
            @InjectView(R.id.icon)
            ImageView mIcon;
            @InjectView(R.id.label)
            TextView mLabel;

            public ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }

        private final int mLayoutResourceId;
        private final PackageManager mPackageManager;

        public Adapter(Context context, @NonNull PackageManager packageManager, @LayoutRes int resource) {
            super(context, resource);
            mPackageManager = packageManager;
            mLayoutResourceId = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            final ResolveInfo info = getItem(position);
            holder.mIcon.setImageDrawable(info.loadIcon(mPackageManager));
            holder.mLabel.setText(info.loadLabel(mPackageManager));

            return convertView;
        }
    }
}
