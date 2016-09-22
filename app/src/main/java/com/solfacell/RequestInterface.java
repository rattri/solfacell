package com.solfacell;

/**
 * Created by Ratri on 9/22/2016.
 */
import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("pricelist.php?a=0")
    Call<JSONResponse> getJSON();
}