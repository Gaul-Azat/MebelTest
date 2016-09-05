package com.example.azat.mebeltest.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.azat.mebeltest.databinding.CalculationItemBinding;
import com.example.azat.mebeltest.modelEntity.Calculation;

import java.util.List;

public class CalculationListAdapter  extends RecyclerView.Adapter<CalculationListAdapter.CalculationViewHolder>{

    List<Calculation> calculations;

    public CalculationListAdapter(List<Calculation> calculations) {
        this.calculations = calculations;
    }


    @Override
    public CalculationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        CalculationItemBinding binding=CalculationItemBinding.inflate(inflater, parent, false);
        return new CalculationViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(CalculationViewHolder holder, int position) {
        holder.binding.setCalc(calculations.get(position));
        if (calculations.get(position).getType()==Calculation.TYPE_IN){
            holder.binding.amountRight.setVisibility(View.VISIBLE);
            holder.binding.statusIN.setVisibility(View.VISIBLE);
            holder.binding.amountLeft.setVisibility(View.GONE);
            holder.binding.statusOUT.setVisibility(View.GONE);
        }else{
            holder.binding.amountLeft.setVisibility(View.VISIBLE);
            holder.binding.statusOUT.setVisibility(View.VISIBLE);
            holder.binding.amountRight.setVisibility(View.GONE);
            holder.binding.statusIN.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return calculations.size();
    }

    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    public static class CalculationViewHolder extends RecyclerView.ViewHolder{

        CalculationItemBinding binding;
        public CalculationViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
