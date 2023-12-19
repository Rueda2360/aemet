package com.pmdm.aplicacionaemet.Model;

public class Prediccion {
    private String temperaturaMin;
    private String temperaturaMax;
    private String probabilidadLluvia;

    public Prediccion(String temperaturaMin, String temperaturaMax, String probabilidadLluvia) {
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
        this.probabilidadLluvia = probabilidadLluvia;
    }

    public String getTemperaturaMin() {
        return temperaturaMin;
    }

    public void setTemperaturaMin(String temperaturaMin) {
        this.temperaturaMin = temperaturaMin;
    }

    public String getTemperaturaMax() {
        return temperaturaMax;
    }

    public void setTemperaturaMax(String temperaturaMax) {
        this.temperaturaMax = temperaturaMax;
    }

    public String getProbabilidadLluvia() {
        return probabilidadLluvia;
    }

    public void setProbabilidadLluvia(String probabilidadLluvia) {
        this.probabilidadLluvia = probabilidadLluvia;
    }



    @Override
    public String toString() {
        return "Prediccion{" +
                "temperaturaMin='" + temperaturaMin + '\'' +
                ", temperaturaMax='" + temperaturaMax + '\'' +
                ", probabilidadLluvia='" + probabilidadLluvia + '\'' +
                '}';
    }
}
