package br.com.amiguapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import controller.ConexaoSocketController;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    Button bProdutos, bLojas;
    RecyclerView recyclerListaItens;

    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchView);
        bProdutos = findViewById(R.id.bProdutos);
        bLojas = findViewById(R.id.bLojas);
        recyclerListaItens = findViewById(R.id.recyclerListaItens);

        informacoesApp = (InformacoesApp) getApplicationContext();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
            }
        });
    }
}