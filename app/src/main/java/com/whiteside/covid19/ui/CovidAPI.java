package com.whiteside.covid19.ui;



import com.whiteside.covid19.model.CountryData;
import com.whiteside.covid19.model.Data;
import com.whiteside.covid19.model.WorldData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CovidAPI {

    @GET("all")
    public Call<WorldData> getWorldData();

    @GET("countries/{country}")
    public Call<CountryData> getCountryData(@Path("country") String country);
}
