package com.example.azat.mebeltest.errorDialogs;

import android.app.Activity;
import android.widget.TextView;

import com.example.azat.mebeltest.modelEntity.Act;
import com.example.azat.mebeltest.service.ModelService;

public class DialogFactory {

    public static ChangeNameDialog getChangeNameDialog(int orderId, ModelService service, TextView nameView){
        ChangeNameDialog dialog=new ChangeNameDialog();
        dialog.setService(service);
        dialog.setId(orderId);
        dialog.setOrderNameView(nameView);
        return dialog;
    }

    public static NetworkErrorDialog getNetworkErrorDialog(Activity activity){
        NetworkErrorDialog dialog=new NetworkErrorDialog();
        dialog.setActivity(activity);
        return dialog;
    }

    public static ServerErrorDialog getServerErrorDialog(Activity activity){
        ServerErrorDialog dialog=new ServerErrorDialog();
        dialog.setActivity(activity);
        return dialog;
    }

    public static ActsDialog getDialogActsOk(Act current) {
        ActsDialog dialog = new ActsDialog();
        dialog.setTitle("Запрос акта");
        dialog.setMessage("Запрос акта успешно выполнен: \n Статус:" + current.getStatus());
        return dialog;
    }

    public static ActsDialog getDialogActsError() {
        ActsDialog dialog=new ActsDialog();
        dialog.setTitle("Запрос акта");
        dialog.setMessage("Время ограничения (24 часа) после предыдущего запроса не прошло");
        return dialog;
    }
}
