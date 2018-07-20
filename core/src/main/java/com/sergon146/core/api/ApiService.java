package com.sergon146.core.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public interface ApiService {

    @GET("api/")
    Observable<Object> getPage();
}
