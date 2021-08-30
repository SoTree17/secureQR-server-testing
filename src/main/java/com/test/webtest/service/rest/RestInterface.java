package com.test.webtest.service.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestInterface {

    @POST("api/v1/secureQR/{resourcePath}")
    Call<ResponseBody> generateQR(@Header("content-type") String contentType, @Path("resourcePath") String path , @Body String params);

}
