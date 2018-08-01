package com.sergon146.core.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("convert?compact=ultra")
    Observable<ResponseBody> getPage(@Query("q") String fromTo);
}
