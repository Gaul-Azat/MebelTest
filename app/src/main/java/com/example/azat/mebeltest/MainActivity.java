package com.example.azat.mebeltest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.azat.mebeltest.adapter.TabsFragmentAdapter;
import com.example.azat.mebeltest.fragment.AbstractTabFragment;
import com.example.azat.mebeltest.service.ModelService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String _token;
    ViewPager viewPager;
    TabsFragmentAdapter adapter;
    ModelService modelService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _token=getIntent().getStringExtra("TOKEN");
        initService();
        initTabs();
    }

    private void initService(){
        if (modelService==null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            AuthInterceptor authInterceptor = new AuthInterceptor(_token);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(logging);
            builder.addInterceptor(authInterceptor);
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.CONNECTION_DEBUG)
                    .client(builder.build())
                    .build();
            modelService = retrofit.create(ModelService.class);
        }
    }

    private void initTabs() {
        adapter = new TabsFragmentAdapter(getApplicationContext(), getSupportFragmentManager(),modelService);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.acion_logout:
                SharedPreferences.Editor preferencesEditor=getSharedPreferences(Constants.PREFERENCE_FILE, Context.MODE_PRIVATE).edit();
                preferencesEditor.putString("TOKEN", "");
                preferencesEditor.apply();
                Intent intent = new Intent(this,SplashActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.action_refresh:

                AbstractTabFragment fragment=(AbstractTabFragment) adapter.getItem(viewPager.getCurrentItem());
                fragment.refreshData();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
