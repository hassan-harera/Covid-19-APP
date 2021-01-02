package com.whiteside.covid19.ui.world;

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
import com.whiteside.covid19.ui.country.CountryActivity;

public class WorldActivity extends AppCompatActivity {

    private TextView all, deaths, recovered;
    private SwipeRefreshLayout refresh;
    private WorldViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        all = findViewById(R.id.all_cases);
        deaths = findViewById(R.id.deaths_cases);
        recovered = findViewById(R.id.recovered_cases);
        refresh = findViewById(R.id.refresh);

        viewModel = ViewModelProviders.of(this).get(WorldViewModel.class);
        viewModel.dataMutableLiveData.observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                all.setText(String.valueOf(data.cases));
                deaths.setText(String.valueOf(data.deaths));
                recovered.setText(String.valueOf(data.recovered));
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
        Intent intent = new Intent(this, CountryActivity.class);
        startActivity(intent);
    }
}