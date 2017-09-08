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

    private int currentThemeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra(KEY_THEME)) {
            currentThemeIndex = getIntent().getIntExtra(KEY_THEME, 0);
        }
        setTheme(getResources().getIdentifier(getResources().getStringArray(R.array.spinner_items)[currentThemeIndex], "style", getPackageName()));

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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        DialogFragment dialog = null;
        switch (view.getId()) {
            case R.id.button1:
                dialog = new SampleAlert();
                break;
            case R.id.button2:
                dialog = new SimpleConfirmationDialog();
                break;
            case R.id.button3:
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

    public void changeTheme(int themeIndex) {
        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY_THEME, themeIndex);
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
            return SampleFragment.newInstance(position == 0, currentThemeIndex);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Enabled";
            } else {
                return "Disabled";
            }
        }
    }
}
