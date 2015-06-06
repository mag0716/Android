package m.k.android.sample.androiddesignsupportlibrarysample;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import m.k.android.sample.androiddesignsupportlibrarysample.fragments.FloatingActionButtonFragment;
import m.k.android.sample.androiddesignsupportlibrarysample.fragments.TextInputLayoutFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;

    // Fragments
    private TextInputLayoutFragment mTextInputLayoutFragment;
    private FloatingActionButtonFragment mFloatingActionButtonFragmentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((NavigationView)findViewById(R.id.navigation)).setNavigationItemSelectedListener(this);

        mTextInputLayoutFragment = TextInputLayoutFragment.newInstance();
        mFloatingActionButtonFragmentFragment = FloatingActionButtonFragment.newInstance();

        mFragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.container, mTextInputLayoutFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        boolean ret = true;

        Toast.makeText(this, "onNavigationItemSelected : " + menuItem.getItemId(), Toast.LENGTH_SHORT).show();

        final FragmentTransaction transaction = mFragmentManager.beginTransaction();

        switch(menuItem.getItemId()) {
            case R.id.navigation_item_1:
                transaction.replace(R.id.container, mTextInputLayoutFragment);
                break;
            case R.id.navigation_item_2:
                transaction.replace(R.id.container, mFloatingActionButtonFragmentFragment);
                break;
            case R.id.navigation_item_3:
                break;
            case R.id.navigation_item_4:
                break;
            case R.id.navigation_item_5:
                break;
            case R.id.navigation_item_6:
                break;
            default:
                ret = false;
                break;
        }

        transaction.commit();

        return ret;
    }
}
