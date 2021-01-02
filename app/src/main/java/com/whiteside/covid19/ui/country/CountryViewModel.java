package com.whiteside.covid19.ui.country;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.whiteside.covid19.model.CountryData;
import com.whiteside.covid19.model.Data;
import com.whiteside.covid19.ui.DataRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends ViewModel {

    MutableLiveData<CountryData> dataMutableLiveData = new MutableLiveData<>();

    public void getCountryData(String countryName) {
        DataRetrofit.getInstance().getCountryData(countryName).enqueue(new Callback<CountryData>() {
            @Override
            public void onResponse(Call<CountryData> call, Response<CountryData> response) {
                dataMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CountryData> call, Throwable t) {

            }
        });
    }
}


//
//    public void getWorldData() {
//        DataRetrofit.getInstance().getWorldData().enqueue(new Callback<Data>() {
//            @Override
//            public void onResponse(Call<Data> call, Response<Data> response) {
//                dataMutableLiveData.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Data> call, Throwable t) {
//
//            }
//        });
//    }