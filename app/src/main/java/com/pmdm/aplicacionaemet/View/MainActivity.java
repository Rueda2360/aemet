package com.pmdm.aplicacionaemet.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pmdm.aplicacionaemet.Controller.MainController;
import com.pmdm.aplicacionaemet.Controller.PrediccionAdapter;
import com.pmdm.aplicacionaemet.Controller.PrediccionViewHolder;
import com.pmdm.aplicacionaemet.Controller.PrediccionViewModel;
import com.pmdm.aplicacionaemet.Model.Prediccion;
import com.pmdm.aplicacionaemet.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private LinkedList<Prediccion> mList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private PrediccionAdapter mAdapter;
    private String[] provincias;
    private ArrayAdapter<String> adaptadorSpinner;
    HashMap<String, String> provinciasMap;
    private EditText editText;
    private Spinner spinner;


    private static MainActivity myActiveActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_prices);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new PrediccionAdapter( this, mList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        PrediccionViewModel vmodel = new ViewModelProvider(this).get(PrediccionViewModel.class);
        vmodel.getPredicciones().observe(this, predicciones -> {


            if(predicciones!=null){
                mList.clear();
                for(Prediccion prediccion: predicciones){
                    mList.add(prediccion);

                }
            }
            mAdapter.notifyDataSetChanged();

        });
//
//        mRecyclerView = findViewById(R.id.rv_prices);
//        // Create an adapter and supply the data to be displayed.
//        mAdapter = new PrediccionAdapter( this, mList);
//        // Connect the adapter with the RecyclerView.
//        mRecyclerView.setAdapter(mAdapter);
//        // Give the RecyclerView a default layout manager.
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainActivity.myActiveActivity = this;
        MainController.setActivity(this);


        editText = findViewById(R.id.editText);
        spinner = findViewById(R.id.spinner);

        provincias = getResources().getStringArray(R.array.provincias);


        //Dividimos las provincias y obtengo solamente la segunda parte, ignorando el CP.
        //Esto es para imprimirlo en el spinner. No quiero el CP en el spinner
        provinciasMap = new HashMap<>();
        for (int i = 0; i < provincias.length; i++) {

            String[] valores = provincias[i].split(",");
            provinciasMap.put(valores[1],valores[0]);

        }

        adaptadorSpinner = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, new ArrayList<>(provinciasMap.keySet()));
        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptadorSpinner);



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                // Filtramos a partir de la tercera letra
                if(charSequence.length()>2){
                    String filtro = charSequence.toString().toLowerCase();
                    adaptadorSpinner.getFilter().filter(filtro);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //Han seleccionado algo
                String elementoSeleccionado = (String) parentView.getItemAtPosition(position);

                Toast.makeText(MainActivity.this, elementoSeleccionado, Toast.LENGTH_LONG).show();

//                vmodel.getPredicciones(provinciasMap.get(elementoSeleccionado));
                MainController.getSingleton().requestData(provinciasMap.get(elementoSeleccionado));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });



    }

    public void errorData(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();

    }

    public void accessData() {
        List<Prediccion> lista= MainController.getSingleton().getData();

        //Put data in adapter
        mList.clear();
        for (Prediccion item:lista) {
            mList.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }



}
