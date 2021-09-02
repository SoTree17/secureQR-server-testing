/*
package com.test.webtest.config;

import com.test.webtest.service.rest.RestInterface;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfig {

    //private static String BaseUrl = "http://ec2-3-37-43-9.ap-northeast-2.compute.amazonaws.com:8080/"; // endpoint
    private static String BaseUrl = "http://localhost:8080/"; // endpoint
    @Bean(name="okHttpClient")
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Bean(name="commonRetrofit")
    public Retrofit retrofit(@Qualifier("okHttpClient") OkHttpClient client){
        return new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client).build();
    }

    @Bean(name="restService")
    public RestInterface restInterface(@Qualifier("commonRetrofit") Retrofit retrofit){
        return retrofit.create(RestInterface.class);
    }
}
*/
