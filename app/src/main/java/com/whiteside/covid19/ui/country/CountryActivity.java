package com.whiteside.covid19.ui.country;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.hbb20.CountryCodePicker;
import com.whiteside.covid19.R;
import com.whiteside.covid19.model.Data;
import com.whiteside.covid19.ui.StatisticsDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryActivity extends AppCompatActivity {

    @BindView(R.id.all_cases)
    TextView all;
    @BindView(R.id.deaths_cases)
    TextView deaths;
    @BindView(R.id.recovered_cases)
    TextView recovered;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.country_picker)
    CountryCodePicker picker;
    private CountryViewModel viewModel;
    private Data data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        ButterKnife.bind(this);

        observeData();
        viewModel.getCountryData(picker.getSelectedCountryName());

        setRefreshListener();
        setPickerListener();
    }

    private void observeData() {
        viewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        viewModel.dataMutableLiveData.observe(this, data -> {
            CountryActivity.this.data = data;
            all.setText(String.valueOf(data.cases));
            deaths.setText(String.valueOf(data.deaths));
            recovered.setText(String.valueOf(data.recovered));
            refresh.setRefreshing(false);

            startAnimation();
        });
    }

    private void startAnimation() {
        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(1)
                .playOn(all);

        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(1)
                .playOn(deaths);

        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(1)
                .playOn(recovered);
    }

    private void setPickerListener() {
        picker.setOnCountryChangeListener(() -> viewModel.getCountryData(picker.getSelectedCountryName()));
    }

    private void setRefreshListener() {
        refresh.setOnRefreshListener(() -> viewModel.getCountryData(picker.getSelectedCountryName()));
    }

    public void backClicked(View view) {
        finish();
    }

    public void moreInfoCLicked(View view) {
        StatisticsDialogFragment fragment = new StatisticsDialogFragment(data);
        fragment.show(getSupportFragmentManager(), "Statistics");
    }
}