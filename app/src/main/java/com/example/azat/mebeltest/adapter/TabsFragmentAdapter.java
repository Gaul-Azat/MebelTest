package com.example.azat.mebeltest.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.azat.mebeltest.fragment.AbstractTabFragment;
import com.example.azat.mebeltest.fragment.CalculationFragment;
import com.example.azat.mebeltest.fragment.NewsFragment;
import com.example.azat.mebeltest.fragment.OrderFragment;
import com.example.azat.mebeltest.service.ModelService;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    ModelService service;

    private OrderFragment orderFragment;
    private CalculationFragment calculationFragment;
    private NewsFragment newsFragment;

    public TabsFragmentAdapter(Context context, FragmentManager fm, ModelService modelService) {
        super(fm);
        this.service=modelService;
        this.context = context;
        initTabsMap(context);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();

        orderFragment = OrderFragment.getInstance(context, service);
        tabs.put(0, orderFragment);
        calculationFragment=CalculationFragment.getInstance(context,service);
        tabs.put(1,calculationFragment);
        newsFragment=NewsFragment.getInstance(context,service);
        tabs.put(2,newsFragment);
    }
}
