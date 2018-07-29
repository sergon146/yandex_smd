package com.sergon146.core;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sergon146.core.api.ApiService;
import com.sergon146.core.rx.RxThreadCallAdapter;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.CipherSuite;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.TlsVersion;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class Core {
    private static Core instance;
    private ApiService apiService;
    private String endpoint;

    public static Core initInstance(String endpoint) {
        if (instance == null) {
            instance = new Core();
        }

        instance.endpoint = endpoint;
        return instance;
    }

    public static ApiService api() {
        return instance.apiService;
    }

    public void initApi() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(createGsonFactory())
                .addCallAdapterFactory(new RxThreadCallAdapter(Schedulers.io()))
                .baseUrl(endpoint);

        apiService = retrofitBuilder
                .client(createClientBuilder().build())
                .build()
                .create(ApiService.class);
    }

    private Converter.Factory createGsonFactory() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return GsonConverterFactory.create(gson);
    }

    private OkHttpClient.Builder createClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(createHttpInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .readTimeout(20, TimeUnit.SECONDS)
                .connectionSpecs(Collections.singletonList(createConnectionSpec()))
                .connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS));
    }

    private Interceptor createHttpInterceptor() {
        return chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder().build();

            Request.Builder requestBuilder = original.newBuilder().url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    private ConnectionSpec createConnectionSpec() {
        return new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build();
    }
}
