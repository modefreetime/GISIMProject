package com.example.map.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.map.mvp.view.fragment.FriendFragment;

public class ContractVpAdapter extends FragmentPagerAdapter {
    public ContractVpAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new FriendFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
