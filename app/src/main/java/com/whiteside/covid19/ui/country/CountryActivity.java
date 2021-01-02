package com.whiteside.covid19.ui.country;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;
import com.whiteside.covid19.R;
import com.whiteside.covid19.model.Data;
import com.whiteside.covid19.ui.world.WorldViewModel;

public class CountryActivity extends AppCompatActivity {

    private static final String TAG = "SearchCountry";
    private TextView all, deaths, recovered;
    private CountryCodePicker picker;
    private SwipeRefreshLayout refresh;
    private CountryViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_country);

        picker = findViewById(R.id.country_picker);
        all = findViewById(R.id.all_cases);
        deaths = findViewById(R.id.deaths_cases);
        recovered = findViewById(R.id.recovered_cases);
        refresh = findViewById(R.id.refresh);

        viewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        viewModel.dataMutableLiveData.observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                all.setText(String.valueOf(data.cases));
                deaths.setText(String.valueOf(deaths));
                recovered.setText(String.valueOf(data.recovered));
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