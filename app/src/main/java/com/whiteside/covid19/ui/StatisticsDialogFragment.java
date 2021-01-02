package com.whiteside.covid19.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.whiteside.covid19.R;
import com.whiteside.covid19.model.Data;
import com.whiteside.covid19.model.ResourcesHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatisticsDialogFragment extends BottomSheetDialogFragment {

    private final Data worldData;
    private List<String> keyList;
    private Map<String, Object> dataMap;

    public StatisticsDialogFragment(Data data) {
        this.worldData = data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new WorldDataAdapter());
    }


    private class WorldDataAdapter extends RecyclerView.Adapter<WorldDataAdapter.ViewHolder> {

        public WorldDataAdapter() {
            keyList = new ArrayList<>();
            Set<String> set = worldData.mapping().keySet();
            keyList.addAll(set);
            dataMap = worldData.mapping();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String key = keyList.get(position);
            holder.key.setText(getResources().getString(ResourcesHandle.getResourceID(key, R.string.class)));
            holder.value.setText(String.valueOf(dataMap.get(key)));
        }

        @Override
        public int getItemCount() {
            return worldData.mapping().size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            final TextView key, value;

            ViewHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.fragment_statistics_dialog_item, parent, false));
                key = itemView.findViewById(R.id.key);
                value = itemView.findViewById(R.id.value);
            }
        }

    }

}