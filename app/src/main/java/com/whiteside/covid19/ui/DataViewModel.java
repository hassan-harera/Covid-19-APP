package com.whiteside.covid19.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.whiteside.covid19.model.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataViewModel extends ViewModel {

    MutableLiveData<Data> dataMutableLiveData = new MutableLiveData<>();


    public void getWorldData(){
        DataRetrofit.getInstance().getWorldData().enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                dataMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    public void getCountryData(String countryName){
        DataRetrofit.getInstance().getCountryData(countryName).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                dataMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }
}
