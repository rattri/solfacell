package com.solfacell.retrofitinterface;

/**
 * Created by Ratri on 9/23/2016.
 */

import com.solfacell.model.ServerRequest;
import com.solfacell.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRegister {

    @POST("learn2crack-login-register/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}