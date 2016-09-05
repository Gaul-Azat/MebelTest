package com.example.azat.mebeltest;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.azat.mebeltest.errorDialogs.DialogFactory;
import com.example.azat.mebeltest.utilsEntity.InitEntity;
import com.example.azat.mebeltest.service.InitService;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    String _token;
    static final int DEVICE_ANDROID=1;
    static final int LOGIN_ACTIVITY=1;

    private Observable<Void> init;
    private Subscription sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _token=getSharedPreferences(Constants.PREFERENCE_FILE, Context.MODE_PRIVATE).getString("TOKEN","");
        if (_token.equals("")){
            Log.d("TOKEN","EMPTY");
            login();
            return;
        }
        Log.d("TOKEN",_token);
        //login();
        initApp();
    }

    private void initApp(){
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        AuthInterceptor authInterceptor=new AuthInterceptor(_token);
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.addInterceptor(logging);
        builder.addInterceptor(authInterceptor);
        Retrofit retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.CONNECTION_DEBUG)
                .client(builder.build())
                .build();
        InitService initService=retrofit.create(InitService.class);

        TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String uuid = tManager.getDeviceId();

        init=initService.initApp(new InitEntity(uuid,DEVICE_ANDROID, Build.DEVICE));
        init.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(current->{
                    goMainActivity();
                },e->{
                    Log.d("SYSTEM_ERROR", e.getMessage());
                    if (e instanceof HttpException) {
                        // We had non-2XX http error
                        if (((HttpException)e).code()==401){
                            login();
                        } else{
                            serverError();
                        }
                    }
                    if (e instanceof IOException) {
                        // A network or conversion error happened
                        networkError();
                    }
                });
    }

    private void networkError() {
        DialogFactory.getNetworkErrorDialog(this).show(getFragmentManager(),"netError");
        //DialogFactory.getServerErrorDialog(this).show(getFragmentManager(),"serverError");
    }

    private void serverError() {
        DialogFactory.getServerErrorDialog(this).show(getFragmentManager(),"serverError");
    }

    private void login(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent,LOGIN_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==LOGIN_ACTIVITY){
            if (resultCode==-1){
                finish();
            }
            if (data!=null){
                _token=data.getStringExtra("TOKEN");
                initApp();
            }
        }
    }

    private void goMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TOKEN",_token);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        if (sub!=null)
            sub.unsubscribe();
        super.onStop();
    }
}
