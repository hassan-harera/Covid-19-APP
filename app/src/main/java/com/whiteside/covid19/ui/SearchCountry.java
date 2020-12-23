package com.whiteside.covid19.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;
import com.whiteside.covid19.R;
import com.whiteside.covid19.model.Data;

public class SearchCountry extends AppCompatActivity {

    private static final String TAG = "SearchCountry";
    private TextView all, deaths, recovered;
    private CountryCodePicker picker;
    private SwipeRefreshLayout refresh;
    private DataViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_country);

        picker = findViewById(R.id.country_picker);
        all = findViewById(R.id.all_cases);
        deaths = findViewById(R.id.deaths_cases);
        recovered = findViewById(R.id.recovered_cases);
        refresh = findViewById(R.id.refresh);

        viewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        viewModel.dataMutableLiveData.observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                all.setText(String.valueOf(data.getCases()));
                deaths.setText(String.valueOf(data.getDeaths()));
                recovered.setText(String.valueOf(data.getRecovered()));
                refresh.setRefreshing(false);
            }
        });
        viewModel.getCountryData(picker.getSelectedCountryName());

        picker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                viewModel.getCountryData(picker.getSelectedCountryName());
            }
        });

        setRefreshListener();
    }

    private void setRefreshListener() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getCountryData(picker.getSelectedCountryName());
            }
        });
    }

    public void backClicked(View view) {
        finish();
    }
}