package com.solfacell;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ArrayAdapter;
import android.app.Activity;
import com.solfacell.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.DefaultItemAnimator;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by Ratri on 9/15/2016.
 */
public class GameFragments extends Fragment {

    private RecyclerView recyclerView;
    private VoucherAdapter adapter;
    private List<Voucher> voucherList;

    private RecyclerView.LayoutManager mLayoutManager;
    public GameFragments() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        voucherList = new ArrayList<>();
        adapter = new VoucherAdapter(this, voucherList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



        prepareVoucher();

        return v;


    }

    /**
     * Adding few albums for testing
     */
    private void prepareVoucher() {
        int[] covers = new int[]{
                R.drawable.garena,
                R.drawable.gemscool,
                R.drawable.googleplay,
                R.drawable.itunes,
                R.drawable.lyto,
                R.drawable.megaxus,
                R.drawable.steam,
               };

        Voucher a = new Voucher("Garena Shell", covers[0]);
        voucherList.add(a);

        a = new Voucher("Gemscool", covers[1]);
        voucherList.add(a);
        a = new Voucher("Google", covers[2]);
        voucherList.add(a);
        a = new Voucher("Itunes", covers[3]);
        voucherList.add(a);
        a = new Voucher("Lyto", covers[4]);
        voucherList.add(a);
        a = new Voucher("Megaxus", covers[5]);
        voucherList.add(a);
        a = new Voucher("Steam Wallet", covers[6]);
        voucherList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}