package com.solfacell;

/**
 * Created by Ratri on 9/22/2016.
 */
import com.solfacell.model.JSONResponse;
import com.solfacell.model.PriceList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestInterface {

    @GET("pricelist.php")
    Call<PriceList> getPrice(@Query("a") String a);
}