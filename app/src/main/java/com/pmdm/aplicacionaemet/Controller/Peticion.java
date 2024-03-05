package com.pmdm.aplicacionaemet.Controller;

import android.os.Handler;
import android.os.Looper;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase Peticion
 *
 * Es utilizado por el controlador. El controlador le proporciona
 * los datos necesarios
 *
 * Se apoyará en OkHttp (librería cliente http/http2)
 *
 */
public class Peticion {
    //ESTADO
    //Clase utilidad que no necesita nada más que poner a funcionar la peticion HTTPs
    private static final String API_KEY = "?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGVydWVkYTIzNjBAZ21haWwuY29tIiwianRpIjoiNTkxNjA2MjUtNzc2YS00ZGMzLTgyNzUtNDdlNjhkMTViNjZjIiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE3MDk2MzgwNzEsInVzZXJJZCI6IjU5MTYwNjI1LTc3NmEtNGRjMy04Mjc1LTQ3ZTY4ZDE1YjY2YyIsInJvbGUiOiIifQ.3e6X34K-AuehxFyTUhPzgHwzSfZ2Zs_W8iBVh5jCltI";
    private String cp;
    private String res;
    //COMPORTAMIENTO
    public Peticion(String cp) {
        this.cp=cp;
    }
    public Peticion(){

    }

    public void requestData(String URL) {
        //La clase para hacer peticion en internet
        OkHttpClient cliente = new OkHttpClient();
        String urlFinal="";
        //Primera llamada al json
        if(!(cp==null)){
            urlFinal=URL+"/"+cp+"/"+API_KEY;
        } else {
            //Segunda llamada al json
            urlFinal=URL;
        }
        this.res=urlFinal;

        //construimos la peticion
        Request peticion = new Request.Builder()
                //.url("https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/horaria/29071/?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGVydWVkYTIzNjBAZ21haWwuY29tIiwianRpIjoiZjVhMzhjMDAtZGM4NC00NDkzLTk4NDUtZGI5OGNlN2M5YTdjIiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE3MDI5MDM0MDgsInVzZXJJZCI6ImY1YTM4YzAwLWRjODQtNDQ5My05ODQ1LWRiOThjZTdjOWE3YyIsInJvbGUiOiIifQ.9NsM99ts3JfvErq7fVOUtHNxcFU0ZAAmePfKoyJaVqk")
                .url(res)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();

        //realizamos la llamada al server, pero en otro thread (con enqueue)
        Call llamada = cliente.newCall(peticion);
        llamada.enqueue(new Callback() {
            public void onResponse(Call call, Response respuestaServer)
                    throws IOException {
                //Got answer!!!!!
                String respuesta = respuestaServer.body().string();
                // Create a handler that associated with Looper of the main thread
                Handler manejador = new Handler(Looper.getMainLooper());
// Send a task to the MessageQueue of the main thread
                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        // Code will be executed on the main thread
                        if(!(cp==null)){
                            MainController.getSingleton().segundaPeticion(respuesta);
                        } else {
                            MainController.getSingleton().setData(respuesta);

                        }
                    }
                });
            }

            public void onFailure(Call call, IOException e) {
                String respuesta = e.getMessage();
                Handler manejador = new Handler(Looper.getMainLooper());

// Send a task to the MessageQueue of the main thread
                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        // Code will be executed on the main thread

                        MainController.getSingleton().setError(respuesta);

                    }
                });
            }
        });





    }

}