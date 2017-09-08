package com.github.mag0716.android_color_definition;


import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mag0716.android_color_definition.databinding.FragmentSampleBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SampleFragment extends Fragment {

    private static final String KEY_ENABLED = "enabled";

    private boolean initialized = false;

    public static SampleFragment newInstance(boolean isEnabled) {
        SampleFragment fragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_ENABLED, isEnabled);
        fragment.setArguments(bundle);
        return fragment;
    }

    public SampleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Bundle arguments = getArguments();

        FragmentSampleBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample, container, false);
        binding.setViewModel(new SampleViewModel(arguments.getBoolean(KEY_ENABLED)));
        return binding.getRoot();
    }

    public class SampleViewModel extends BaseObservable {

        private final boolean isEnabled;

        SampleViewModel(boolean isEnabled) {
            this.isEnabled = isEnabled;
        }

        public boolean isEnabled() {
            return isEnabled;
        }

    }
}
