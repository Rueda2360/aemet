package com.pmdm.aplicacionaemet.Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.pmdm.aplicacionaemet.Model.Prediccion;

import java.util.LinkedList;
import java.util.List;


public class Respuesta {
    //ESTADO
    protected String datos;
    //COMPORTAMIENTO
    public Respuesta(String entrada) {
        datos = entrada;
    }

    public String getNuevaURL() {

        try {

            JSONObject jsonObject = new JSONObject(datos);

            String json2 = jsonObject.getString("datos");
            return json2;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Prediccion> getData() {

        List<Prediccion> resultado = new LinkedList<>();

        // Crear un array JSON
        try {
            JSONArray jsonArray = new JSONArray(datos);

            JSONObject jsonObject = jsonArray.getJSONObject(0);

            // Obtener la lista de "dia"
            JSONArray listaDia = jsonObject.getJSONObject("prediccion").getJSONArray("dia");

            for(int i=0;i< listaDia.length();i++){
                JSONObject jTemp = listaDia.getJSONObject(i);

                String probPrecipitacion = jTemp.getJSONArray("probPrecipitacion").getJSONObject(0).getString("value");
                ;
                JSONObject temperatura = jTemp.getJSONObject("temperatura");

                String temperaturaMaxima = temperatura.getString("maxima");
                String temperaturaMinima = temperatura.getString("minima");

                resultado.add(new Prediccion(temperaturaMinima,temperaturaMaxima,probPrecipitacion));
            }

         int a =0;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }



}
