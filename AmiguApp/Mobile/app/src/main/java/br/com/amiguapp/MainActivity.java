package br.com.amiguapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import controller.ConexaoSocketController;
import modelDominio.Produto;
import modelDominio.Vendedor;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerListaItens;
    ProdutoAdapter produtoAdapter;
    InformacoesApp informacoesApp;

    ArrayList<Produto> listaProdutos;
    ArrayList<Vendedor> listaVendedores;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchView);
        recyclerListaItens = findViewById(R.id.recyclerListaItens);
        informacoesApp = (InformacoesApp) getApplicationContext();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                listaVendedores = conexaoSocket.listaVendedores();
                listaProdutos = conexaoSocket.listaProdutos();
                if (listaProdutos != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(listaVendedores.size());

                            produtoAdapter = new ProdutoAdapter(listaProdutos, trataCliqueItem, listaVendedores);
                            recyclerListaItens.setLayoutManager(new GridLayoutManager(context, 2));
                            recyclerListaItens.setItemAnimator(new DefaultItemAnimator());
                            recyclerListaItens.setAdapter(produtoAdapter);
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível obter a lista dos produtos!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        thread.start();
    }

    ProdutoAdapter.ProdutoOnClickListener trataCliqueItem = new ProdutoAdapter.ProdutoOnClickListener() {
        @Override
        public void onClickProduto(View view, int position) {
            Produto meuProduto = listaProdutos.get(position);
            Toast.makeText(informacoesApp, "Produto clicado", Toast.LENGTH_SHORT).show();
        }
    };
}