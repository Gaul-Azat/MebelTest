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
import com.example.azat.mebeltest.adapter.OrderListAdapter;
import com.example.azat.mebeltest.modelEntity.Order;
import com.example.azat.mebeltest.errorDialogs.DialogFactory;
import com.example.azat.mebeltest.service.ModelService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.order_fragment;

    private List<Order> orders;

    private OrderListAdapter adapter;

    RecyclerView rv;
    TextView noOrdersView;

    ModelService service;

    public static OrderFragment getInstance(Context context, ModelService modelService) {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setData(new ArrayList<>());
        fragment.setService(modelService);
        fragment.setRetainInstance(true);
        fragment.setTitle(context.getString(R.string.order_tab));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        rv = (RecyclerView) view.findViewById(R.id.orderRecycleView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        noOrdersView=(TextView)view.findViewById(R.id.no_orders);
        adapter = new OrderListAdapter(orders,service,getContext());
        rv.setAdapter(adapter);
        if (orders==null||orders.size()==0){
            rv.setVisibility(View.GONE);
            noOrdersView.setVisibility(View.VISIBLE);
        } else {
            rv.setVisibility(View.VISIBLE);
            noOrdersView.setVisibility(View.GONE);
        }
        if(orders==null||orders.size()==0) {
            loadOrders();
            Log.d("calc","orders");
        }
        return view;
    }

    public void refreshList(List<Order> data) {
        if (data==null||data.size()==0){
            rv.setVisibility(View.GONE);
            noOrdersView.setVisibility(View.VISIBLE);
        } else {
            rv.setVisibility(View.VISIBLE);
            noOrdersView.setVisibility(View.GONE);
            adapter.setOrders(data);
            adapter.notifyDataSetChanged();
            orders=data;
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<Order> data) {
        this.orders = data;
    }

    public void setService(ModelService service) {
        this.service = service;
    }

    private void loadOrders() {
        Observable<List<Order>> order=service.getOrders();
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
        loadOrders();
    }
}


