package com.example.btvn_json.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btvn_json.R;
import com.example.btvn_json.entity.Weather;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    List<Weather> weathers;

    public WeatherAdapter(List<Weather> weathers) {
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.items,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        holder.tvWeather.setText(weathers.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWeather;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeather = itemView.findViewById(R.id.tvWeather_Des);
        }
    }
}

