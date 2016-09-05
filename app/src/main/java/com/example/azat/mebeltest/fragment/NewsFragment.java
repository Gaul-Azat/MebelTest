package com.example.azat.mebeltest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.azat.mebeltest.R;
import com.example.azat.mebeltest.adapter.NewsListAdapter;
import com.example.azat.mebeltest.modelEntity.News;
import com.example.azat.mebeltest.errorDialogs.DialogFactory;
import com.example.azat.mebeltest.service.ModelService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.news_fragment;

    private List<News> newsList;

    private NewsListAdapter adapter;

    ModelService service;
    RecyclerView rv;
    TextView noNewsView;

    public static NewsFragment getInstance(Context context, ModelService modelService) {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setData(new ArrayList<>());
        fragment.setService(modelService);
        fragment.setRetainInstance(true);
        fragment.setTitle(context.getString(R.string.news_tab));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        rv = (RecyclerView) view.findViewById(R.id.newsRecycleView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        noNewsView=(TextView)view.findViewById(R.id.no_news);
        adapter = new NewsListAdapter(newsList);
        rv.setAdapter(adapter);
        if (newsList==null||newsList.size()==0){
            rv.setVisibility(View.GONE);
            noNewsView.setVisibility(View.VISIBLE);
        } else {
            rv.setVisibility(View.VISIBLE);
            noNewsView.setVisibility(View.GONE);
        }
        loadNews();
        return view;
    }

    public void refreshList(List<News> data) {
        if (data==null||data.size()==0){
            rv.setVisibility(View.GONE);
            noNewsView.setVisibility(View.VISIBLE);
        } else {
            rv.setVisibility(View.VISIBLE);
            noNewsView.setVisibility(View.GONE);
            adapter.setNewsList(data);
            adapter.notifyDataSetChanged();
            newsList=data;
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<News> data) {
        this.newsList = data;
    }

    public void setService(ModelService service) {
        this.service = service;
    }

    private void loadNews() {
        Observable<List<News>> order=service.getNews();
        order.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(current->refreshList(current),e->{
                    Log.d("SYSTEM_ERROR", e.getMessage());
                    if (e instanceof HttpException) {
                        // We had non-2XX http error
                        serverError();
                    }
                    if (e instanceof IOException) {
                        // A network or conversion error happened
                        networkError();
                    }
                });
    }

    private void networkError() {
        DialogFactory.getNetworkErrorDialog(getActivity()).show(getActivity().getFragmentManager(),"netError");
    }

    private void serverError() {
        DialogFactory.getServerErrorDialog(getActivity()).show(getActivity().getFragmentManager(),"serverError");
    }

    @Override
    public void refreshData() {
        loadNews();
    }
}
