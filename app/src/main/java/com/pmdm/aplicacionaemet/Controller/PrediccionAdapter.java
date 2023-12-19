package com.pmdm.aplicacionaemet.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pmdm.aplicacionaemet.Model.Prediccion;
import com.pmdm.aplicacionaemet.R;

import java.util.LinkedList;
import java.util.List;


public class PrediccionAdapter extends RecyclerView.Adapter<PrediccionViewHolder> {

    private final LinkedList<Prediccion> mList;
    private LayoutInflater mInflater;

    public PrediccionAdapter(Context context, List<Prediccion> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = (LinkedList<Prediccion>) list;
    }


    @NonNull
    @Override
    public PrediccionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.inflar, parent, false);
        return new PrediccionViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PrediccionViewHolder holder, int position) {
        holder.setMinima("Mínima: " + this.mList.get(position).getTemperaturaMin());
        holder.setMaxima("Máxima: " + this.mList.get(position).getTemperaturaMax());
        holder.setPrecipitacion("Probabilidad:" + this.mList.get(position).getProbabilidadLluvia());
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

}
