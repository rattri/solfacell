package com.solfacell.adapter;

/**
 * Created by Ratri on 9/20/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.solfacell.Order;
import com.solfacell.PriceListActivity;
import com.solfacell.R;
import com.solfacell.fragments.GameFragments;
import com.solfacell.model.PriceList;

import java.util.List;

public class AdapterPriceList extends RecyclerView.Adapter<AdapterPriceList.ViewHolder> {
    private List<PriceList.Voucher> voucher;
    private PriceListActivity mContext;
    private String harga;
    private String nama;
    private Activity mActivity;
    public AdapterPriceList(List<PriceList.Voucher> voucher, Activity activity) {
        this.voucher = voucher;
        mActivity = activity;
    }

    @Override
    public AdapterPriceList.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.price_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterPriceList.ViewHolder viewHolder, int i) {
        String idr;
        idr="IDR ";
        PriceList.Voucher vcr = voucher.get(i);
        viewHolder.tv_nama.setText("IDR "+vcr.getNama());
        viewHolder.tv_harga.setText("Rp "+vcr.getHarga());
        nama = vcr.getNama();
        harga = vcr.getHarga();
        viewHolder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mActivity, Order.class);
                mActivity.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return voucher.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nama, tv_harga;
        private View listItem;
        public ViewHolder(View view) {
            super(view);
            listItem = (View) view.findViewById(R.id.list_item);
            tv_nama = (TextView)view.findViewById(R.id.text_nama);
            tv_harga = (TextView)view.findViewById(R.id.text_harga);

            };
        }
    }


