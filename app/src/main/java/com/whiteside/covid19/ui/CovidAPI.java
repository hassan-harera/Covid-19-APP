package com.whiteside.covid19.ui;



import com.whiteside.covid19.model.Data;
import com.whiteside.covid19.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CovidAPI {

    @GET("all")
    public Call<Data> getWorldData();

    @GET("countries/{country}")
    public Call<Data> getCountryData(@Path("country") String country);
}
