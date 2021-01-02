package com.whiteside.covid19.ui;


import com.whiteside.covid19.model.Data;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRetrofit {

    private static final String BASE_URL = "https://disease.sh/v3/covid-19/";
    private static DataRetrofit INSTANCE;
    private CovidAPI covidAPI;

    private DataRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        covidAPI = retrofit.create(CovidAPI.class);
    }

    public static DataRetrofit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataRetrofit();
        }
        return INSTANCE;
    }

    public Call<Data> getWorldData(){
        return covidAPI.getWorldData();
    }

    public Call<Data> getCountryData(String countryName){
        return covidAPI.getCountryData(countryName);
    }
}
