package com.example.vinh.trainingw4.mon;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vinh.trainingw4.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/25/2017.
 */
class MondayActivity extends AppCompatActivity {
    private static final int[] mTabIcons = {
            R.drawable.ic_like,
            R.drawable.ic_profile,
            R.drawable.ic_setting,
    };
    private static final int[] mTabIconsSelected = {
            R.drawable.ic_liked,
            R.drawable.ic_profile_selected,
            R.drawable.ic_setting_selected
    };

    private List<String> mUrls = new ArrayList<>();
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        mUrls.add("http://orig12.deviantart.net/e196/f/2014/349/b/e/i_hate_you__i_love_you__zoro_x_reader__by_riseagainstevil-d88ovwj.png");
        mUrls.add("http://chibi.info/wp-content/uploads/2016/12/anh-luffy-chibi-4.png");
        mUrls.add("https://s-media-cache-ak0.pinimg.com/originals/49/c9/0d/49c90d31044ae688aaf6cc94d1ef4e83.png");

        ViewPagerAdapter mAdapter = new ViewPagerAdapter(this, mUrls);
        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mUrls);

        mViewPager.setAdapter(mAdapter);
//        mTabLayout.setupWithViewPager(mViewPager);
//        customTabView(TabType.TEXT_ONLY);
    }

    private void customTabView(TabType tabType) {
        if (tabType == TabType.BOTH_TEXT_ICON) {
            for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_item, null);
                tabOne.setText(getString(R.string.tab_name));
                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, mTabIcons[i], 0, 0);
                mTabLayout.getTabAt(i).setCustomView(tabOne);
            }
            return;
        }

        if (tabType == TabType.ICON_ONLY) {
            for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                mTabLayout.getTabAt(i).setIcon(mTabIcons[i]);
            }
            return;
        }

        if (tabType == TabType.TEXT_ONLY) {
            // Do nothing
        }
    }

    class ViewPagerAdapter extends PagerAdapter {
        private final LayoutInflater mLayoutInflater;
        private final Context mContext;
        private List<String> mUrls = new ArrayList<>();

        ViewPagerAdapter(Context context, List<String> urls) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
            mUrls = urls;
        }

        @Override
        public int getCount() {
            return mUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imgContent);
            Glide.with(mContext).load(mUrls.get(position)).into(imageView);
            container.addView(itemView);
            return itemView;
        }
    }

    class FragmentPagerAdapter extends FragmentStatePagerAdapter {
        private List<String> mUrls = new ArrayList<>();

        FragmentPagerAdapter(FragmentManager fm, List<String> urls) {
            super(fm);
            mUrls = urls;
        }

        @Override
        public Fragment getItem(int position) {
            return OnePieceFragment.getInstance(mUrls.get(position));
        }

        @Override
        public int getCount() {
            return mUrls.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + position;
        }
    }
}
