package com.example.azat.mebeltest.errorDialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.azat.mebeltest.R;
import com.example.azat.mebeltest.modelEntity.Order;
import com.example.azat.mebeltest.service.ModelService;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ChangeNameDialog extends DialogFragment  {

    EditText changeNameEdit;
    ModelService service;
    int id;
    TextView orderNameView;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder b=  new  AlertDialog.Builder(getActivity())
                .setTitle("Изменение названия заказа")
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Observable<Order> changeName=service.changeNameOrder(id,changeNameEdit.getText().toString());
                                changeName.subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(current-> {
                                            orderNameView.setText(current.getTitleToString());
                                            dialog.dismiss();
                                        },e->{
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
                        }
                )
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );

        LayoutInflater i = getActivity().getLayoutInflater();

        View v = i.inflate(R.layout.change_name_dialog_fragment,null);
        changeNameEdit=(EditText)v.findViewById(R.id.changeNameEdit);
        b.setView(v);
        return b.create();
    }

    public void setService(ModelService service) {
        this.service = service;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderNameView(TextView orderName) {
        this.orderNameView = orderName;
    }


    private void networkError() {
    }

    private void serverError() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
