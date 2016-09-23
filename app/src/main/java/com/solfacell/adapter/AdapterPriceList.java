package com.solfacell.adapter;

/**
 * Created by Ratri on 9/20/2016.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.solfacell.R;
import com.solfacell.model.PriceList;

import java.util.List;

public class AdapterPriceList extends RecyclerView.Adapter<AdapterPriceList.ViewHolder> {
    private List<PriceList.Voucher> voucher;

    public AdapterPriceList(List<PriceList.Voucher> voucher) {
        this.voucher = voucher;
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
        viewHolder.tv_nama.setText(vcr.getNama());
        viewHolder.tv_harga.setText(vcr.getHarga());


    }

    @Override
    public int getItemCount() {
        return voucher.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nama, tv_harga;
        public ViewHolder(View view) {
            super(view);

            tv_nama = (TextView)view.findViewById(R.id.text_nama);
            tv_harga = (TextView)view.findViewById(R.id.text_harga);

        }
    }
}


