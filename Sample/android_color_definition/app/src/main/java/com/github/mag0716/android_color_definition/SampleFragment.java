package com.github.mag0716.android_color_definition;


import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.github.mag0716.android_color_definition.databinding.FragmentSampleBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SampleFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String KEY_ENABLED = "enabled";
    private static final String KEY_THEME = "theme";

    private boolean initialized = false;

    public static SampleFragment newInstance(boolean isEnabled, int themeIndex) {
        SampleFragment fragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_ENABLED, isEnabled);
        bundle.putInt(KEY_THEME, themeIndex);
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
        binding.setViewModel(new SampleViewModel(arguments.getBoolean(KEY_ENABLED), arguments.getInt(KEY_THEME, 0)));
        binding.spinner.setOnItemSelectedListener(this);
        return binding.getRoot();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (initialized && getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).changeTheme(position);
        }
        initialized = true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class SampleViewModel extends BaseObservable {

        private final boolean isEnabled;
        private final int spinnerIndex;

        SampleViewModel(boolean isEnabled, int spinnerIndex) {
            this.isEnabled = isEnabled;
            this.spinnerIndex = spinnerIndex;
        }

        public boolean isEnabled() {
            return isEnabled;
        }

        public int getSpinnerIndex() {
            return spinnerIndex;
        }
    }
}
