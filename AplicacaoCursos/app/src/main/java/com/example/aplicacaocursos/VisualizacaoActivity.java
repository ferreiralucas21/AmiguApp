package com.example.aplicacaocursos;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class VisualizacaoActivity extends AppCompatActivity {
    ListView lvVisualizacaoCursos;

    InformacoesApp informacoesApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao);
        Toolbar toolbar = findViewById(R.id.toolbar);
        lvVisualizacaoCursos = findViewById(R.id.lvVisualizacaoCursos);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //obtendo o contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        //carregando o listView
        //1 - declarando o vetor auxiliar que conterá os nomes dos cursos
        String[] nomesCursos = new String[informacoesApp.getListaCursos().size()];

        //2 - navegando na lista para a obtenção dos cursos e dos respectivos nomes
        for (int i = 0; i < informacoesApp.getListaCursos().size(); i++) {
            //obtendo o objeto da classe curso
            Curso meuCurso = informacoesApp.getListaCursos().get(i);
            //carrendo o vetor com o nome do curso
            nomesCursos[i] = meuCurso.getNome();

        }

        //3 - definindo o conteúdo (adapter) do listView
        lvVisualizacaoCursos.setAdapter(new ArrayAdapter<String>(VisualizacaoActivity.this, android.R.layout.simple_list_item_1, nomesCursos));

        lvVisualizacaoCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Curso meuCurso = informacoesApp.getListaCursos().get(i);

                Toast.makeText(informacoesApp, "Nome: " + meuCurso.getNome()
                        + "\nCarga horária: " + meuCurso.getCargaHoraria()
                        + "\nConceito: " + meuCurso.getConceito()
                        + "\nTipo: " + meuCurso.getTipoLiteral(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
