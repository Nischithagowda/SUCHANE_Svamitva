package com.bmc.suchane_svamitva.api;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient_Suchane {
    private static Retrofit retrofit;
    private static Retrofit retrofitWithoutToken;

    public static Retrofit getClient(Context context, String url) {
        if (retrofit == null) {

            OkHttpClient client = UnsafeOkHttpClient.getUnsafeOkHttpClient(context);

//            OkHttpClient.Builder client = new OkHttpClient
//                    .Builder();
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            if (BuildConfig.DEBUG) {
//                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            } else {
//                logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//            }
//            client.connectTimeout(30, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .writeTimeout(30, TimeUnit.SECONDS)
//                    .addInterceptor(logging)
//                    .authenticator(new AccessTokenAuthenticator(context))
//            ;
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setLenient()
                            .create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static Retrofit getClientWithoutToken(String url) {
        if (retrofitWithoutToken == null) {

            OkHttpClient client = UnsafeOkHttpClientForToken.getUnsafeOkHttpClient();
//            OkHttpClient.Builder client = new OkHttpClient
//                    .Builder();
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            if (BuildConfig.DEBUG) {
//                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            } else {
//                logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//            }
//            client.connectTimeout(30, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .writeTimeout(30, TimeUnit.SECONDS)
//                    .addInterceptor(logging);

            retrofitWithoutToken = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setLenient()
                            .create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }

        return retrofitWithoutToken;
    }

}
