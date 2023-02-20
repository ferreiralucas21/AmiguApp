package br.com.amiguapp;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import controller.ConexaoSocketController;
import modelDominio.Cliente;
import modelDominio.Encomenda;
import modelDominio.Produto;
import modelDominio.Vendedor;

public class PerfilUsuarioActivity extends AppCompatActivity {

    EditText etPerfilUsuarioCpf, etPerfilUsuarioEmail, etPerfilUsuarioNome, etPerfilUsuarioTelefone,
            etPerfilUsuarioRua, etPerfilUsuarioBairro, etPerfilUsuarioComplemento, etPerfilUsuarioCep;
    Button bPerfilUsuarioEditar;
    ImageView appbarIconSeta, appbarIconHome;
    RecyclerView recyclerListaPedidos;
    ArrayList<Encomenda> listaEncomendas;
    ArrayList<Vendedor> listaVendedores;
    InformacoesApp informacoesApp;
    PedidoAdapter pedidoAdapter;
    Context context;
    Cliente clienteAlterado;

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
        appbarIconSeta = findViewById(R.id.appbarIconSeta);
        appbarIconHome = findViewById(R.id.appbarIconHome);

        recyclerListaPedidos = findViewById(R.id.recyclerListaPedidos);


        CliqueSeta();
        CliqueHome();

        informacoesApp = (InformacoesApp) getApplicationContext();

        Cliente cliente = informacoesApp.getClienteLogado();
        etPerfilUsuarioCpf.setText(cliente.getCpf());
        etPerfilUsuarioEmail.setText(cliente.getEmail());
        etPerfilUsuarioNome.setText(cliente.getNome());
        etPerfilUsuarioTelefone.setText(cliente.getTelefone());
        etPerfilUsuarioRua.setText(cliente.getRua());
        etPerfilUsuarioBairro.setText(cliente.getBairro());
        etPerfilUsuarioComplemento.setText(cliente.getComplemento());
        etPerfilUsuarioCep.setText(cliente.getCep());

        bPerfilUsuarioEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etPerfilUsuarioCpf.getText().toString().equals("")){
                    if (!etPerfilUsuarioEmail.getText().toString().equals("")){
                        if (!etPerfilUsuarioNome.getText().toString().equals("")){
                            if (!etPerfilUsuarioTelefone.getText().toString().equals("")){
                                int idCliente = cliente.getIdCliente();
                                String cpf = etPerfilUsuarioCpf.getText().toString();
                                String email = etPerfilUsuarioEmail.getText().toString();
                                String nome = etPerfilUsuarioNome.getText().toString();
                                String telefone = etPerfilUsuarioTelefone.getText().toString();
                                String rua = etPerfilUsuarioRua.getText().toString();
                                String bairro = etPerfilUsuarioBairro.getText().toString();
                                String complemento = etPerfilUsuarioComplemento.getText().toString();
                                String cep = etPerfilUsuarioCep.getText().toString();

                                clienteAlterado = new Cliente(idCliente,cpf, email, nome, telefone, rua, bairro, complemento, cep);

                                Thread thread1 = new Thread(() -> {
                                    ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                                    String msgRecebida = conexaoSocket.alterarCliente(clienteAlterado);

                                    if (msgRecebida.equals("ok")) {
                                        // sugiro que o servidor retorne o usuário com todas as informações do banco. Esse objeto pode estar na InformacoesApp e ser usado em futuras necessidades
                                        informacoesApp.setClienteLogado(clienteAlterado);
                                        runOnUiThread(() -> Toast.makeText(informacoesApp, "Informações editadas com sucesso", Toast.LENGTH_SHORT).show());
                                    } else {
                                        System.out.println(clienteAlterado.toString());
                                        runOnUiThread(() -> Toast.makeText(informacoesApp, "Erro ao realizar as alterações", Toast.LENGTH_SHORT).show());
                                    }

                                });
                                thread1.start();

                            }else{
                                etPerfilUsuarioTelefone.setError("Informe o telefone");
                                etPerfilUsuarioTelefone.requestFocus();
                            }
                        }else {
                            etPerfilUsuarioNome.setError("Informe o nome");
                            etPerfilUsuarioNome.requestFocus();
                        }
                    }else {
                        etPerfilUsuarioEmail.setError("Informe o email");
                        etPerfilUsuarioEmail.requestFocus();
                    }
                }else {
                    etPerfilUsuarioCpf.setError("Informe o CPF");
                    etPerfilUsuarioCpf.requestFocus();
                }
            }
        });

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
                            pedidoAdapter = new PedidoAdapter(listaEncomendas, trataCliqueItem);
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


    private void CliqueHome() {
        appbarIconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(PerfilUsuarioActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

    private void CliqueSeta() {
        appbarIconSeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(PerfilUsuarioActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
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