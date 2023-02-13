package br.com.amiguapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

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
    ArrayList<Vendedor> listaVendedores;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.appbarSearchView);
        appbarIconPerfil = findViewById(R.id.appbarIconPerfil);
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
            Vendedor meuVendedor = null;
            informacoesApp.setProdutoSelecionado(meuProduto);
            for (int i = 0; i <= listaVendedores.size(); i++) {
                if (listaVendedores.get(i).getIdVendedor() == meuProduto.getFkIdVendedor()) {
                    meuVendedor = listaVendedores.get(i);
                    break;
                }
            }
            Intent it = new Intent(MainActivity.this, ProdutoDetalhadoActivity.class);
            it.putExtra("produtoClicado", meuProduto);
            it.putExtra("vendedorDoProduto",meuVendedor);
            startActivity(it);
        }
    };
}