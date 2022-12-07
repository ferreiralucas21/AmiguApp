package com.example.aplicacaocursos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class VisualizacaoRecyclerActivity extends AppCompatActivity {
    RecyclerView rvVisualizacaoCursos;
    CursoAdapter cursoAdapter;

    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_recycler);
        rvVisualizacaoCursos = findViewById(R.id.rvVisualizacaoCursos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        informacoesApp = (InformacoesApp) getApplicationContext();

        if (informacoesApp.getListaCursos() != null){
            cursoAdapter = new CursoAdapter(informacoesApp.getListaCursos(), trataCliqueItem);
            rvVisualizacaoCursos.setLayoutManager(new LinearLayoutManager(VisualizacaoRecyclerActivity.this));
            rvVisualizacaoCursos.setItemAnimator(new DefaultItemAnimator());
            rvVisualizacaoCursos.setAdapter(cursoAdapter);
        }
    }

    CursoAdapter.CursoOnClickListener trataCliqueItem = new CursoAdapter.CursoOnClickListener() {
        @Override
        public void onClickCurso(View view, int position) {
            Curso meuCurso = informacoesApp.getListaCursos().get(position);

            //criando a intent para a chamada da tela de detalhamento
            Intent it = new Intent(VisualizacaoRecyclerActivity.this, VisualizacaoDetalhadaActivity.class);
            //adicioanando o curso na solicitação
            it.putExtra("curso", meuCurso);
            //enviando a solicitação ao sistema
            startActivity(it);

        }
    };

}
