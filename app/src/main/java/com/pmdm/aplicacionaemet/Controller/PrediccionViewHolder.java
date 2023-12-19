package com.pmdm.aplicacionaemet.Controller;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pmdm.aplicacionaemet.R;


public class PrediccionViewHolder extends RecyclerView.ViewHolder {

    //ESTADO

    //TODO: declare attributes to hold two textviews, day and money
    final PrediccionAdapter mAdapter;
    private TextView tvMinima;
    private TextView tvMaxima;
    private TextView tvPrecipitacion;

    // ...


    //COMPORTAMIENTO
    public PrediccionViewHolder(View itemView, PrediccionAdapter adapter) {
        super(itemView);

        tvMinima = itemView.findViewById(R.id.tvMinima);
        tvMaxima = itemView.findViewById(R.id.tvMaxima);
        tvPrecipitacion = itemView.findViewById(R.id.tvPrecipitacion);
        this.mAdapter = adapter;
    }

    public void setMinima(String data) {
        tvMinima.setText(data);
    }

    public void setMaxima(String data) {
        tvMaxima.setText(data);
    }

    public void setPrecipitacion(String data) {
        tvPrecipitacion.setText(data);
    }

}
