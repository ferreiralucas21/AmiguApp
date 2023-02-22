package br.com.amiguapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import controller.ConexaoSocketController;
import modelDominio.Produto;
import modelDominio.Vendedor;

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
        searchView = findViewById(R.id.appbarSearchView);
        appbarIconPerfil = findViewById(R.id.appbarIconPerfil);
        recyclerListaItens = findViewById(R.id.recyclerListaItens);
        informacoesApp = (InformacoesApp) getApplicationContext();


        appbarIconPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, PerfilUsuarioActivity.class);
                startActivity(it);
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                listaProdutos = conexaoSocket.listaProdutos();
                if (listaProdutos != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            produtoAdapter = new ProdutoAdapter(listaProdutos, trataCliqueItem);

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
                                        //Checa se o nome do produto contem o que foi pesquisado, o tolowercase serve pra nao dar problema de maiusculo e minusculo pq transforma tudo em minusculo
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
            informacoesApp.setProdutoSelecionado(meuProduto);
            Intent it = new Intent(MainActivity.this, ProdutoDetalhadoActivity.class);
            it.putExtra("produtoClicado", meuProduto);
            startActivity(it);
        }
    };
}