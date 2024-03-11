package com.pmdm.aplicacionaemet.Controller;

import com.pmdm.aplicacionaemet.Model.Prediccion;
import com.pmdm.aplicacionaemet.View.MainActivity;

import java.util.LinkedList;
import java.util.List;

public class MainController {

    //SINGLETON Controller
    private static final String DATA_URL = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria";
    private static MainController mySingleController;

    private List<Prediccion> dataRequested;
    private PrediccionViewModel myViewModel;


    private static MainActivity activeActivity;
    //Comportamiento
    //Constructor
    private MainController() {

        dataRequested = new LinkedList<Prediccion>();
        myViewModel=null;
    }

    //Get instance
    public static MainController getSingleton() {
        if (MainController.mySingleController == null) {
            mySingleController = new MainController();
        }
        return mySingleController;
    }

    //To send data to view
    public List<Prediccion> getData() {
        return this.dataRequested;
    }

    //Called from the view
//    public void conseguirCP(String cp) {
//        //A día de hoy esto es inútil
//        requestData(cp);
//
//    }
    public void requestData(String cp) {

        Peticion p = new Peticion(cp);
        p.requestData(DATA_URL);
//        this.myViewModel=myViewModel;
    }
    public void segundaPeticion(String respuesta) {
        Respuesta answer = new Respuesta(respuesta);
        String nuevaURL = answer.getNuevaURL();

        Peticion p = new Peticion();
        p.requestData(nuevaURL);
    }

    //Called when onResponse is OK
    public void setData(String json) {

        Respuesta answer = new Respuesta(json);

        dataRequested = answer.getData();

        // Imprimir datos para verificar
       /* for (Partido partido : dataRequested) {
            Log.d("DataDebug", "Equipo1: " + partido.getEquipo1() +
                    ", Equipo2: " + partido.getEquipo2() +
                    ", Resultado: " + partido.getResultado());
        }*/
        //Load data on the list
        if (myViewModel!=null) myViewModel.setData(dataRequested);
//        MainController.activeActivity.accessData();
    }
    public List<Prediccion> loadViewModel(PrediccionViewModel myViewModel){
        this.myViewModel=myViewModel;
        return dataRequested;
    }
    public void setError(String error) {

        //Load data on the list
        MainController.activeActivity.errorData(error);
    }


    public static void setActivity(MainActivity myAct) {
        activeActivity = myAct;
    }

    public List<Prediccion> getList() {
        return this.dataRequested;
    }

}
