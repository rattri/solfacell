package com.solfacell;

/**
 * Created by Ratri on 9/22/2016.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.solfacell.model.PriceList;
import com.solfacell.retrofitinterface.RequestInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PriceListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PriceList.Voucher> data;
    private AdapterPriceList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_price_list);
        initViews();
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