package com.example.aplicacaocursos;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class RelatorioActivity extends AppCompatActivity {
    CheckBox cbRelatorioQuantidadeTipo, cbRelatorioMediaCarga, cbRelatorioMelhorCurso;
    Button bRelatorioVerificar;

    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cbRelatorioQuantidadeTipo = findViewById(R.id.cbRelatorioQuantidadeTipo);
        cbRelatorioMediaCarga = findViewById(R.id.cbRelatorioMediaCarga);
        cbRelatorioMelhorCurso = findViewById(R.id.cbRelatorioMelhorCurso);
        bRelatorioVerificar = findViewById(R.id.bRelatorioVerificar);

        informacoesApp = (InformacoesApp) getApplicationContext();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bRelatorioVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            //declarando as variáveis auxiliares
            public void onClick(View view) {

                int quantidadeTecnologo = 0, quantidadeBacharelado = 0, quantidadeLicenciatura = 0;
                float somaCargasHorarias = 0;
                int maiorConceito = Integer.MIN_VALUE;
                Curso melhorCurso = null;

                //navegando na lista de cursos
                for (int i = 0; i < informacoesApp.getListaCursos().size(); i++){
                    //obtendo cada um dos objetos
                    Curso meuCurso = informacoesApp.getListaCursos().get(i);

                    //verificando os tipos
                    if (meuCurso.getTipo() == 1) {
                        quantidadeTecnologo++;
                    } else if (meuCurso.getTipo() == 2) {
                        quantidadeBacharelado++;
                    } else if (meuCurso.getTipo() == 3) {
                        quantidadeLicenciatura++;
                    }

                    somaCargasHorarias = somaCargasHorarias + meuCurso.getCargaHoraria();

                    if (meuCurso.getConceito() > maiorConceito) {
                        maiorConceito = meuCurso.getConceito();
                        melhorCurso = meuCurso;
                    }
                }

                //verificando a seleção do checkbox
                if (cbRelatorioQuantidadeTipo.isChecked()){
                    Toast.makeText(informacoesApp, "QUANTIDADES\n\nTecnologo: " + quantidadeTecnologo + "\nBacharelado: " + quantidadeBacharelado + "\nLicenciatura: " + quantidadeLicenciatura, Toast.LENGTH_SHORT).show();

                }

                if (cbRelatorioMediaCarga.isChecked()){
                    float mediaCargaHoraria = somaCargasHorarias / informacoesApp.getListaCursos().size();
                    Toast.makeText(informacoesApp, "Média das cargas horárias: " + mediaCargaHoraria, Toast.LENGTH_SHORT).show();
                }

                if (cbRelatorioMelhorCurso.isChecked()){
                    Toast.makeText(informacoesApp, "MELHOR CURSO\n\nNome:" +melhorCurso.getNome()
                            + "\nCarga horária:" + melhorCurso.getCargaHoraria()
                            + "\nTipo: " + melhorCurso.getTipoLiteral(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
