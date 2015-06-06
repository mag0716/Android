package m.k.android.sample.androiddesignsupportlibrarysample.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.Toast;

import m.k.android.sample.androiddesignsupportlibrarysample.R;

public class TabLayoutFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TextInputLayoutFragment.
     */
    public static TabLayoutFragment newInstance() {
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TabLayoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);

        TabLayout tabLayout1 = (TabLayout)view.findViewById(R.id.tab1);
        TabLayout tabLayout2 = (TabLayout)view.findViewById(R.id.tab2);
        for(int i=0; i<10; i++) {
            tabLayout1.addTab(tabLayout1.newTab().setText(String.format("Tab%d", i + 1)));
            tabLayout2.addTab(tabLayout2.newTab().setText(String.format("Tab%d", i + 1)));
        }
        tabLayout1.setOnTabSelectedListener(this);
        tabLayout2.setOnTabSelectedListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Toast.makeText(getActivity(), "onTabSelected : " + tab.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Toast.makeText(getActivity(), "onTabUnselected : " + tab.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Toast.makeText(getActivity(), "onTabReselected : " + tab.getText(), Toast.LENGTH_SHORT).show();
    }
}
