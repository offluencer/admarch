package com.admarch.offluence.utils;

import android.content.Context;

import com.admarch.offluence.fragments.HomeFragment;
import com.admarch.offluence.fragments.LeaderBoardFragment;
import com.admarch.offluence.fragments.LeaderFragment;
import com.admarch.offluence.fragments.ProfileFragment;
import com.admarch.offluence.model.LeaderBoard;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
//import com.codewithgolap.tablayout.Fragments.ChatsFragment;
//import com.codewithgolap.tablayout.Fragments.HomeFragment;
//import com.codewithgolap.tablayout.Fragments.SettingsFragment;

public class SectionPageAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    public SectionPageAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }
    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            case 2:
                LeaderFragment leaderFragment = new LeaderFragment();
                return leaderFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
