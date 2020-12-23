package com.whiteside.covid19.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.whiteside.covid19.R;
import com.whiteside.covid19.model.Data;

public class HomePage extends AppCompatActivity {

    private TextView all, deaths, recovered;
    private SwipeRefreshLayout refresh;
    private DataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

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
                recovered.setText(String.valueOf( data.getRecovered()));
                refresh.setRefreshing(false);
            }
        });

        viewModel.getWorldData();
        setRefreshListener();
    }

    private void setRefreshListener() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getWorldData();
            }
        });
    }

    public void searchClicked(View view) {
        Intent intent = new Intent(this, SearchCountry.class);
        startActivity(intent);
    }
}