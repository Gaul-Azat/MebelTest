package com.example.azat.mebeltest.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import java.io.Serializable;

public abstract class AbstractTabFragment extends Fragment{

    private String title;
    protected Context context;
    protected View view;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    abstract public void refreshData();
}
