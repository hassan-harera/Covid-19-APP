package com.whiteside.covid19.ui.world;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.whiteside.covid19.R;
import com.whiteside.covid19.model.Data;
import com.whiteside.covid19.ui.StatisticsDialogFragment;
import com.whiteside.covid19.ui.country.CountryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorldActivity extends AppCompatActivity {

    @BindView
            (R.id.all_cases)
    TextView all;
    @BindView
            (R.id.deaths_cases)
    TextView deaths;
    @BindView
            (R.id.recovered_cases)
    TextView recovered;
    @BindView
            (R.id.refresh)
    SwipeRefreshLayout refresh;
    private WorldViewModel viewModel;
    private Data worldData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        ButterKnife.bind(this);

        observeData();

        viewModel.getWorldData();

        setRefreshListener();
    }

    private void observeData() {
        viewModel = ViewModelProviders.of(this).get(WorldViewModel.class);
        viewModel.dataMutableLiveData.observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                worldData = data;
                all.setText(String.valueOf(data.cases));
                deaths.setText(String.valueOf(data.deaths));
                recovered.setText(String.valueOf(data.recovered));
                refresh.setRefreshing(false);

                startAnimation();
            }
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

    public void moreInfoCLicked(View view) {
        StatisticsDialogFragment fragment = new StatisticsDialogFragment(worldData);
        fragment.show(getSupportFragmentManager(), "Statistics");
    }
}