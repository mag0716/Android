package m.k.android.sample.androiddesignsupportlibrarysample;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import m.k.android.sample.androiddesignsupportlibrarysample.fragments.AppBarLayoutFragment;
import m.k.android.sample.androiddesignsupportlibrarysample.fragments.CollapsingToolbarLayoutFragment;
import m.k.android.sample.androiddesignsupportlibrarysample.fragments.CoordinatorLayoutFragment;
import m.k.android.sample.androiddesignsupportlibrarysample.fragments.FloatingActionButtonFragment;
import m.k.android.sample.androiddesignsupportlibrarysample.fragments.SnackBarFragment;
import m.k.android.sample.androiddesignsupportlibrarysample.fragments.TabLayoutFragment;
import m.k.android.sample.androiddesignsupportlibrarysample.fragments.TextInputLayoutFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;

    // Fragments
    private TextInputLayoutFragment mTextInputLayoutFragment;
    private FloatingActionButtonFragment mFloatingActionButtonFragmentFragment;
    private TabLayoutFragment mTabLayoutFragment;
    private CoordinatorLayoutFragment mCoordinatorLayoutFragment;
    private AppBarLayoutFragment mAppBarLayoutFragment;
    private CollapsingToolbarLayoutFragment mCollapsingToolbarLayoutFragment;
    private SnackBarFragment mSnackBarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        mNavigationView = ((NavigationView)findViewById(R.id.navigation));
        mNavigationView.setNavigationItemSelectedListener(this);

        mTextInputLayoutFragment = TextInputLayoutFragment.newInstance();
        mFloatingActionButtonFragmentFragment = FloatingActionButtonFragment.newInstance();
        mTabLayoutFragment = TabLayoutFragment.newInstance();
        mCoordinatorLayoutFragment = CoordinatorLayoutFragment.newInstance();
        mAppBarLayoutFragment = AppBarLayoutFragment.newInstance();
        mCollapsingToolbarLayoutFragment = CollapsingToolbarLayoutFragment.newInstance();
        mSnackBarFragment = SnackBarFragment.newInstance();

        mFragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.container, mTextInputLayoutFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        boolean ret = true;

        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        menuItem.setChecked(true);

        switch(menuItem.getItemId()) {
            case R.id.navigation_item_1:
                transaction.replace(R.id.container, mTextInputLayoutFragment);
                break;
            case R.id.navigation_item_2:
                transaction.replace(R.id.container, mFloatingActionButtonFragmentFragment);
                break;
            case R.id.navigation_item_3:
                transaction.replace(R.id.container, mTabLayoutFragment);
                break;
            case R.id.navigation_item_4:
                transaction.replace(R.id.container, mCoordinatorLayoutFragment);
                break;
            case R.id.navigation_item_5:
                transaction.replace(R.id.container, mAppBarLayoutFragment);
                break;
            case R.id.navigation_item_6:
                transaction.replace(R.id.container, mCollapsingToolbarLayoutFragment);
                break;
            case R.id.navigation_item_7:
                transaction.replace(R.id.container, mSnackBarFragment);
                break;
            default:
                ret = false;
                break;
        }

        transaction.commit();

        mDrawer.closeDrawers();

        return ret;
    }
}
