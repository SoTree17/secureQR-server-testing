package com.test.webtest.service.Restrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestInterface {

    @POST("api/v1/secureQR/{resourcePath}")
    Call<Object> generateQR(@Path("resourcePath") String path , @Body String params);
    // Call<ResponseBody>은 서버 응답을 ResponseBody 데이터 형식으로 받겠다는 의미.
    //(@Header("content-type") String contentType,
    // ResponseEntity<QrImage>
    // <QRImage>
}
