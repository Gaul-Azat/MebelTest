package com.example.azat.mebeltest.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.azat.mebeltest.databinding.NewsItemBinding;
import com.example.azat.mebeltest.modelEntity.News;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>{

    List<News> newsList;

    public NewsListAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        NewsItemBinding binding=NewsItemBinding.inflate(inflater, parent, false);
        return new NewsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.binding.setNews(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        NewsItemBinding binding;
        public NewsViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
