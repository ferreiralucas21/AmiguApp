package br.com.amiguapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import controller.ConexaoSocketController;
import modelDominio.Encomenda;
import modelDominio.Produto;
import modelDominio.Vendedor;

public class PerfilUsuarioActivity extends AppCompatActivity {

    EditText etPerfilUsuarioCpf, etPerfilUsuarioEmail, etPerfilUsuarioNome, etPerfilUsuarioTelefone,
            etPerfilUsuarioRua, etPerfilUsuarioBairro, etPerfilUsuarioComplemento, etPerfilUsuarioCep;
    Button bPerfilUsuarioEditar;
    RecyclerView recyclerListaPedidos;
    ArrayList<Encomenda> listaEncomendas;
    ArrayList<Vendedor> listaVendedores;
    InformacoesApp informacoesApp;
    PedidoAdapter pedidoAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        etPerfilUsuarioCpf = findViewById(R.id.etPerfilUsuarioCpf);
        etPerfilUsuarioEmail = findViewById(R.id.etPerfilUsuarioEmail);
        etPerfilUsuarioNome = findViewById(R.id.etPerfilUsuarioNome);
        etPerfilUsuarioTelefone = findViewById(R.id.etPerfilUsuarioTelefone);
        etPerfilUsuarioRua = findViewById(R.id.etPerfilUsuarioRua);
        etPerfilUsuarioBairro = findViewById(R.id.etPerfilUsuarioBairro);
        etPerfilUsuarioComplemento = findViewById(R.id.etPerfilUsuarioComplemento);
        etPerfilUsuarioCep = findViewById(R.id.etPerfilUsuarioCep);
        bPerfilUsuarioEditar = findViewById(R.id.bPerfilUsuarioEditar);

        recyclerListaPedidos = findViewById(R.id.recyclerListaPedidos);

        informacoesApp = (InformacoesApp) getApplicationContext();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                listaEncomendas = conexaoSocket.listaEncomendas();
                listaVendedores = conexaoSocket.listaVendedores();
                if (listaEncomendas != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pedidoAdapter = new PedidoAdapter(listaEncomendas, trataCliqueItem, listaVendedores);
                            recyclerListaPedidos.setLayoutManager(new LinearLayoutManager(informacoesApp));
                            recyclerListaPedidos.setItemAnimator(new DefaultItemAnimator());
                            recyclerListaPedidos.setAdapter(pedidoAdapter);
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

    PedidoAdapter.ProdutoOnClickListener trataCliqueItem = new PedidoAdapter.ProdutoOnClickListener() {
        @Override
        public void onClickProduto(View view, int position) {
//            Encomenda minhaEncomenda = listaEncomendas.get(position);
//            Vendedor meuVendedor = null;
//            informacoesApp.setProdutoSelecionado(meuProduto);
//            for (int i = 0; i <= listaVendedores.size(); i++) {
//                if (listaVendedores.get(i).getIdVendedor() == meuProduto.getFkIdVendedor()) {
//                    meuVendedor = listaVendedores.get(i);
//                    break;
//                }
//            }
//            Intent it = new Intent(MainActivity.this, ProdutoDetalhadoActivity.class);
//            it.putExtra("produtoClicado", meuProduto);
//            it.putExtra("vendedorDoProduto",meuVendedor);
//            startActivity(it);
        }
    };
}