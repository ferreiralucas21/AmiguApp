package com.example.aplicacaocursos;

import android.app.Application;

import java.util.ArrayList;

public class InformacoesApp extends Application {

    private ArrayList<Curso> listaCursos;

    @Override
    public void onCreate() {
        super.onCreate();
        this.listaCursos = new ArrayList<>();
    }

    public ArrayList<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(ArrayList<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }
}
