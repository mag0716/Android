package com.github.mag0716.android_color_definition;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.mag0716.android_color_definition.dialog.SampleAlert;
import com.github.mag0716.android_color_definition.dialog.SimpleConfirmationDialog;
import com.github.mag0716.android_color_definition.dialog.SimpleDatePickerDialog;
import com.github.mag0716.android_color_definition.dialog.SimpleTimePickerDialog;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_THEME = "Theme";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter adapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager viewPager;
    private TabLayout tab;
    private FloatingActionButton fab;
    ;
    private String currentTheme = "AppTheme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra(KEY_THEME)) {
            currentTheme = getIntent().getStringExtra(KEY_THEME);
        }
        setTheme(getResources().getIdentifier(currentTheme, "style", getPackageName()));

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        tab = findViewById(R.id.tab);
        // デフォルトは MODE_FIXED
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fab.setEnabled(position == 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tab.setupWithViewPager(viewPager);
        tab.getTabAt(4).setIcon(R.drawable.ic_local_phone_24dp);
        tab.getTabAt(5).setIcon(R.drawable.ic_local_phone_24dp);
        tab.getTabAt(6).setIcon(R.drawable.ic_person_pin_24dp);
        tab.getTabAt(7).setIcon(R.drawable.ic_person_pin_24dp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        changeTheme((String) item.getTitle());
        return true;
    }

    public void onClick(View view) {
        DialogFragment dialog = null;
        switch (view.getId()) {
            case R.id.button1:
                dialog = new SampleAlert();
                break;
            case R.id.button2:
                dialog = new SimpleConfirmationDialog();
                dialog.setArguments(SimpleConfirmationDialog.createArguments(false));
                break;
            case R.id.button3:
                dialog = new SimpleConfirmationDialog();
                dialog.setArguments(SimpleConfirmationDialog.createArguments(true));
                break;
            case R.id.button4:
                dialog = new SimpleDatePickerDialog();
                break;
            case R.id.button5:
                dialog = new SimpleTimePickerDialog();
                break;
        }
        if (dialog != null) {
            dialog.show(getSupportFragmentManager(), dialog.getClass().getSimpleName());
        }
    }

    public void changeTheme(String theme) {
        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY_THEME, theme);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return SampleFragment.newInstance(position % 2 == 0);
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Enabled";
                case 1:
                    return "Disabled";
                case 2:
                    return "Enabled\nEnabled";
                case 3:
                    return "Disabled\nDisabled";
                case 4:
                case 5:
                    return "";
                case 6:
                    return "Enabled";
                case 7:
                    return "Disabled";
            }
            return null;
        }
    }
}
