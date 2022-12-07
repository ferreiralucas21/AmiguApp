package com.example.aplicacaocursos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    TextView tvMenuSaudacao;
    Button bMenuCadastro, bMenuRelatorio, bMenuVisualizacao, bMenuVisualizacaoRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tvMenuSaudacao = findViewById(R.id.tvMenuSaudacao);
        bMenuCadastro = findViewById(R.id.bMenuCadastro);
        bMenuRelatorio = findViewById(R.id.bMenuRelatorio);
        bMenuVisualizacao = findViewById(R.id.bMenuVisualizacao);
        bMenuVisualizacaoRecycler = findViewById(R.id.bMenuVisualizacaoRecycler);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //obtendo os parâmetos da tela anterior
        final Intent it = getIntent(); //pegando a chamada
        // verificando a intent e os parâmetros
        if (it != null && it.hasExtra("usuario")){
            //obtendo o parâmetro racebido
            String usuario = it.getStringExtra("usuario");
            // mostrando na tela a informação recebida
            tvMenuSaudacao.setText(usuario + ", seja bem vindo");
        }

        bMenuCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //criando a Intent para a chamada da tela de Cadastro
                Intent it = new Intent(MenuActivity.this, CadastroActivity.class);
                //enviando a Intent para o sistema operacional
                startActivity(it);
            }
        });

        bMenuRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //criando a Intent para a chamada da tela de cadastro
                Intent it = new Intent(MenuActivity.this, RelatorioActivity.class);
                //enviando a Intent para o sistema operacional
                startActivity(it);

            }
        });

        bMenuVisualizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //criando a Intent para a chamada da tela de visualização
                Intent it = new Intent(MenuActivity.this, VisualizacaoActivity.class);
                //enviando a Intent para o sistema operacional
                startActivity(it);
            }
        });

        bMenuVisualizacaoRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MenuActivity.this, VisualizacaoRecyclerActivity.class);
                startActivity(it);
            }
        });




    }

}
