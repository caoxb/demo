package xbcao.demo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xbcao.demo.fragment.CityFragment;
import xbcao.demo.fragment.EatFragment;
import xbcao.demo.fragment.ShopFragment;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        CityFragmentPagerAdapter adapter = new CityFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private class CityFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] mTitles = new String[]{"CITY GUIDE", "SHOP", "EAT"};

        public CityFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            if (position == 1) {
                return new ShopFragment();
            } else if (position == 2) {
                return new EatFragment();
            }
            return new CityFragment();
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
