package com.pmdm.aplicacionaemet.Controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.pmdm.aplicacionaemet.Model.Prediccion;

import java.util.List;

public class PrediccionViewModel extends ViewModel {

    private MutableLiveData<List<Prediccion>> listaPredicciones;

    public LiveData<List<Prediccion>> getPredicciones() {
        if (listaPredicciones == null) {
            listaPredicciones = new MutableLiveData<List<Prediccion>>();
            loadPrecios();

        }
        return listaPredicciones;
    }

    private void loadPrecios() {
        // Do an asynchronous operation to fetch precios.
        //aqui instancias sigleton
        List<Prediccion> listaValores = MainController.getSingleton().loadViewModel(this);
        listaPredicciones.setValue(listaValores);
    }


    public void setData(List<Prediccion> list) {

        listaPredicciones.postValue(list);
    }
}

