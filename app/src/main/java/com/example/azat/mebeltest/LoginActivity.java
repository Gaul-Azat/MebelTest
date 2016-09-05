package com.example.azat.mebeltest;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.azat.mebeltest.errorDialogs.DialogFactory;
import com.example.azat.mebeltest.utilsEntity.LoginForm;
import com.example.azat.mebeltest.utilsEntity.TokenEntity;
import com.example.azat.mebeltest.service.AuthService;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    ProgressDialog _loginDialog;
    String _token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _emailText=(EditText)findViewById(R.id.input_email);
        _passwordText=(EditText)findViewById(R.id.input_password);
        _loginButton=(Button)findViewById(R.id.btn_login);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        _loginButton.setEnabled(false);
        if (!validate()){
            onLoginFailed();
        }
        _loginDialog = new ProgressDialog(LoginActivity.this);
        _loginDialog.setIndeterminate(true);
        _loginDialog.setMessage("Аутентификация...");
        _loginDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.CONNECTION_DEBUG)
                .client(httpClient.build())
                .build();
        AuthService autorizationService=retrofit.create(AuthService.class);


        SharedPreferences.Editor preferencesEditor=getSharedPreferences(Constants.PREFERENCE_FILE, Context.MODE_PRIVATE).edit();
        Observable<TokenEntity> auth1=autorizationService.login(new LoginForm(email,password));
        auth1.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(current->{
                    if (current.accessToken!=null) {
                        preferencesEditor.putString("TOKEN", current.accessToken);
                        preferencesEditor.apply();
                        _token=current.accessToken;
                        onLoginSuccess();
                    }},e->{
                    Log.d("SYSTEM_ERROR", e.getMessage());
                    if (e instanceof HttpException) {
                            serverError();
                    }
                    if (e instanceof IOException) {
                        // A network or conversion error happened
                        networkError();
                        Log.d("NETWORK_ERROR",e.getMessage());
                    }
                });
    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        if (_loginDialog!=null){
            _loginDialog.dismiss();
        }
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra("TOKEN",_token);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void onLoginFailed() {
        if (_loginDialog!=null){
            _loginDialog.dismiss();
        }
        Toast.makeText(getBaseContext(), "Не удалось авторизоваться", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !Patterns.PHONE.matcher(email).matches()) {
            _emailText.setError("введите вверный логин");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        return valid;
    }
    private void networkError() {
        DialogFactory.getNetworkErrorDialog(this).show(getFragmentManager(),"netError");
    }

    private void serverError() {
        DialogFactory.getServerErrorDialog(this).show(getFragmentManager(),"serverError");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (_loginDialog!=null){
            _loginDialog.dismiss();
        }
    }
}
