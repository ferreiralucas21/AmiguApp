package br.com.amiguapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import controller.ConexaoSocketController;
import modelDominio.Produto;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    ImageView appbarIconPerfil;
    RecyclerView recyclerListaItens;
    ProdutoAdapter produtoAdapter;
    InformacoesApp informacoesApp;

    ArrayList<Produto> listaProdutos;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        informacoesApp = (InformacoesApp) getApplicationContext();
        vinculaElementos();

        cliquePerfil();

        Thread thread = carregaListaDeProdutos();
        thread.start();
    }

    @NonNull
    private Thread carregaListaDeProdutos() {
        Thread thread = new Thread(() -> {
            ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
            listaProdutos = conexaoSocket.listaProdutos();
            if (listaProdutos != null) {
                runOnUiThread(() -> {

                    produtoAdapter = new ProdutoAdapter(listaProdutos, trataCliqueItem);

                    configuraBarraDePesquisa();
                    recyclerListaItens.setLayoutManager(new GridLayoutManager(context, 2));
                    recyclerListaItens.setItemAnimator(new DefaultItemAnimator());
                    recyclerListaItens.setAdapter(produtoAdapter);
                });

            } else {
                runOnUiThread(() -> Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível obter a lista dos produtos!", Toast.LENGTH_SHORT).show());
            }
        });
        return thread;
    }

    private void configuraBarraDePesquisa() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Produto> listaFiltrada = new ArrayList<>();
                for (Produto produto :
                        listaProdutos) {
                    if (produto.getNome().toLowerCase().contains(newText.toLowerCase())){
                        listaFiltrada.add(produto);
                    }
                }
                produtoAdapter.search(listaFiltrada);
                if (listaFiltrada.isEmpty()){
                    Toast.makeText(informacoesApp, "Sem produtos desse nome", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private void cliquePerfil() {
        appbarIconPerfil.setOnClickListener(view -> {
            Intent it = new Intent(MainActivity.this, PerfilUsuarioActivity.class);
            startActivity(it);
        });
    }

    private void vinculaElementos() {
        searchView = findViewById(R.id.appbarSearchView);
        appbarIconPerfil = findViewById(R.id.appbarIconPerfil);
        recyclerListaItens = findViewById(R.id.recyclerListaItens);
    }

    ProdutoAdapter.ProdutoOnClickListener trataCliqueItem = new ProdutoAdapter.ProdutoOnClickListener() {
        @Override
        public void onClickProduto(View view, int position) {
            Produto meuProduto = listaProdutos.get(position);
            informacoesApp.setProdutoSelecionado(meuProduto);
            Intent it = new Intent(MainActivity.this, ProdutoDetalhadoActivity.class);
            it.putExtra("produtoClicado", meuProduto);
            startActivity(it);
        }
    };
}