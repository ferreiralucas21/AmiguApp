package com.example.aplicacaocursos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class VisualizacaoDetalhadaActivity extends AppCompatActivity {
    TextView tvVisualizaNome, tvVisualizaCargaHoraria, tvVisualizaConceito, tvVisualizaTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_detalhada);
        tvVisualizaNome = findViewById(R.id.tvVisualizaNome);
        tvVisualizaCargaHoraria = findViewById(R.id.tvCadastroCargaHoraria);
        tvVisualizaConceito = findViewById(R.id.tvVisualizaConceito);
        tvVisualizaTipo = findViewById(R.id.tvVisualizaTipo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //obtendo a solicitação
        Intent it = getIntent();
        //verificando se existem os parâmetro
        if (it != null && it.hasExtra("curso")) {
            //obtendo o parâmetro
            Curso meuCurso = (Curso) it.getSerializableExtra("curso");
            //carreegando a tela a partir do curso recebido
            tvVisualizaNome.setText(meuCurso.getNome());
            tvVisualizaCargaHoraria.setText(String.valueOf(meuCurso.getCargaHoraria()));
            tvVisualizaConceito.setText(String.valueOf(meuCurso.getConceito()));
            tvVisualizaTipo.setText(meuCurso.getTipoLiteral());
        }
    }

}
