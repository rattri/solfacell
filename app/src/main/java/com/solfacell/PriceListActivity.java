package com.solfacell;

/**
 * Created by Ratri on 9/22/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.solfacell.adapter.AdapterPriceList;
import com.solfacell.model.PriceList;
import com.solfacell.retrofitinterface.RequestInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PriceListActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    private RecyclerView recyclerView;
    private List<PriceList.Voucher> data;
    private AdapterPriceList adapter;
    private SharedPreferences pref;
    public TextView yourname;
    public TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pref = getSharedPreferences(Constants.LOGIN_OPERATION, Context.MODE_PRIVATE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        welcome = (TextView) header.findViewById(R.id.welcome);
        setupMenuView();
        initViews();
    }
    private void setupMenuView(){
        if (!pref.getBoolean(Constants.IS_LOGGED_IN, false)) {
            navigationView.inflateMenu(R.menu.login);
            welcome.setText("Kamu belum login");
        } else {
            navigationView.inflateMenu(R.menu.activity_main_login);
            welcome.setText("Selamat Datang "+ pref.getString(Constants.NAME, ""));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.beranda) {
            // Handle the camera action
        }
        else if (id == R.id.logout){
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean(Constants.IS_LOGGED_IN,false);
            editor.putString(Constants.EMAIL,"");
            editor.putString(Constants.NAME,"");
            editor.putString(Constants.UNIQUE_ID,"");
            editor.apply();

            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Kamu berhasil logout")
                    .show();
            Intent i = new Intent(this,  MainActivity.class);
            startActivity(i);
        }
        else if (id == R.id.login) {
            Intent i = new Intent(this,  LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        data = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://solfacell.solfagaming.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<PriceList> call = request.getPrice("0");
        call.enqueue(new Callback<PriceList>() {
            @Override
            public void onResponse(Call<PriceList> call, Response<PriceList> response) {
                data = response.body().getVoucher();
                for(PriceList.Voucher voucher : data){
                    System.out.println(voucher.getNama());
                }

                adapter = new AdapterPriceList(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PriceList> call, Throwable t) {

            }
        });
    }
}