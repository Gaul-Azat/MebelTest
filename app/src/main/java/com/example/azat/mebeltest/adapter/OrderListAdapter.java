package com.example.azat.mebeltest.adapter;


import android.app.FragmentManager;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.azat.mebeltest.errorDialogs.DialogFactory;
import com.example.azat.mebeltest.R;
import com.example.azat.mebeltest.databinding.OrderItemBinding;
import com.example.azat.mebeltest.modelEntity.Order;
import com.example.azat.mebeltest.service.ModelService;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder> {

    List<Order> orders;

    ModelService service;
    Context context;

    public OrderListAdapter(List<Order> orders, ModelService service, Context context) {
        this.orders = orders;
        this.service = service;
        this.context = context;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setService(ModelService service) {
        this.service = service;
    }

    public void setLocation(Context location) {
        this.context = location;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        OrderItemBinding binding=OrderItemBinding.inflate(inflater, parent, false);
        return new OrderViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.binding.setOrder(orders.get(position));
        holder.binding.title.setOnClickListener(view -> {
            FragmentActivity activity=(FragmentActivity)context;
            FragmentManager fm=activity.getFragmentManager();
            DialogFactory.getChangeNameDialog(orders.get(position).getId(),service,holder.binding.title).show(fm,"change_name");
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Integer type=Integer.decode(v);
        int id;
        switch (type){
            case 1:
                id=R.drawable.order_creation;
                break;
            case 2:
                id=R.drawable.order_prepayment;
                break;
            case 3:
                id=R.drawable.order_process;
                break;
            case 4:
                id=R.drawable.order_waiting;
                break;
            case 5:
                id=R.drawable.order_production;
                break;
            case 6:
                id=R.drawable.order_ready;
                break;
            case 7:
                id=R.drawable.order_realized;
                break;
            case 8:
                id=R.drawable.order_shipped;
                break;
            default:
                id=R.drawable.order_shipped;
                Log.d("bh",type+"");
        }
        imageView.setImageResource(id);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        OrderItemBinding binding;
        public OrderViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}

