package com.example.azat.mebeltest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.azat.mebeltest.R;
import com.example.azat.mebeltest.adapter.CalculationListAdapter;
import com.example.azat.mebeltest.modelEntity.Act;
import com.example.azat.mebeltest.modelEntity.Calculation;
import com.example.azat.mebeltest.modelEntity.Profile;
import com.example.azat.mebeltest.errorDialogs.DialogFactory;
import com.example.azat.mebeltest.service.ModelService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CalculationFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.calculation_fragment;

    private List<Calculation> calculations;

    private CalculationListAdapter adapter;

    ModelService service;
    TextView balanceStartView;
    TextView balanceEndView;

    double balanceStart=1;
    double balanceEnd=1;

    public static CalculationFragment getInstance(Context context, ModelService modelService) {
        Bundle args = new Bundle();
        CalculationFragment fragment = new CalculationFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setData(new ArrayList<>());
        fragment.setService(modelService);
        fragment.setTitle(context.getString(R.string.calculcation_tab));

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.calculationRecycleView);
        rv.setLayoutManager(new LinearLayoutManager(context));

        adapter = new CalculationListAdapter(calculations);
        rv.setAdapter(adapter);
        balanceStartView=(TextView)view.findViewById(R.id.balance_start);
        balanceEndView=(TextView)view.findViewById(R.id.balance_end);
        FloatingActionButton actFab=(FloatingActionButton)view.findViewById(R.id.act_fab);
        actFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActs(view.getContext());
            }
        });
        if (calculations==null||calculations.size()==0)
        {
            loadCalculations();
            Log.d("calc","load");
        }
        if (balanceEnd==1&&balanceStart==1)
        {
            loadBalance();
        }else{
            refreshBalance(balanceStart,balanceEnd);
        }
        return view;
    }

    private void refreshCalculations(List<Calculation> data) {
        adapter.setCalculations(data);
        adapter.notifyDataSetChanged();
        this.calculations=data;
    }

    private void refreshBalance(double balanceStart,double balanceEnd){
        balanceStartView.setText("Начальный остаток: "+balanceStart);
        balanceEndView.setText("Конечный остаток: "+balanceEnd);
        this.balanceStart=balanceStart;
        this.balanceEnd=balanceEnd;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<Calculation> data) {
        this.calculations = data;
    }

    public void setService(ModelService service) {
        this.service = service;
    }

    private void loadActs(Context context) {
        Observable<Act> act=service.requestActs();
        act.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(current->DialogFactory.getDialogActsOk(current).show(getActivity().getFragmentManager(),"ActsOk"),e->{
                    Log.d("SYSTEM_ERROR", e.getMessage());
                    if (e instanceof HttpException) {
                        // We had non-2XX http error
                        if (((HttpException)e).code()==409)
                            DialogFactory.getDialogActsError().show(getActivity().getFragmentManager(),"ActsOk");
                        else
                            serverError();
                    }
                    if (e instanceof IOException) {
                        // A network or conversion error happened
                        networkError();
                    }
                });
    }

    private void loadCalculations() {
        Observable<ArrayList<Calculation>> order=service.getCalculations();
        order.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(current->refreshCalculations(current),e->{
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

    private void loadBalance() {
        Observable<Profile> profile=service.getProfile();
        profile.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(current->refreshBalance(current.getBalanceStart(),current.getBalanceEnd()),e->{
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
        loadBalance();
        loadCalculations();
    }
}
