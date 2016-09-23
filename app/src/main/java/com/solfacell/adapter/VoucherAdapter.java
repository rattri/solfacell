package com.solfacell.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.solfacell.fragments.GameFragments;
import com.solfacell.PriceListActivity;
import com.solfacell.R;
import com.solfacell.model.Voucher;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.MyViewHolder> {

    private GameFragments mContext;
    private List<Voucher> voucherList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
  ;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext.getContext(), PriceListActivity.class);
                    mContext.startActivity(i);

                }
            });

            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext.getContext(), PriceListActivity.class);
                    mContext.startActivity(i);

                }
            });

        }
    }


    public VoucherAdapter(GameFragments mContext, List<Voucher> voucherList) {
        this.mContext = mContext;
        this.voucherList = voucherList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Voucher voucher = voucherList.get(position);
        holder.title.setText(voucher.getName());

        // loading album cover using Glide library
        Glide.with(mContext).load(voucher.getThumbnail()).into(holder.thumbnail);

    }




    @Override
    public int getItemCount() {
        return voucherList.size();
    }
}