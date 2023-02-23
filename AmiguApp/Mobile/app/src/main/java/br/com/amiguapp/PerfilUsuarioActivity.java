package br.com.amiguapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
    ArrayList<Produto> listaProdutos;
    InformacoesApp informacoesApp;
    PedidoAdapter pedidoAdapter;
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


        cliqueSeta();
        cliqueHome();

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

                                clienteAlterado = new Cliente(idCliente,nome, email, telefone, cpf, rua, bairro, complemento, cep);

                                Thread thread1 = new Thread(() -> {
                                    ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                                    String msgRecebida = conexaoSocket.alterarCliente(clienteAlterado);

                                    if (msgRecebida.equals("ok")) {
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
                listaEncomendas = conexaoSocket.listaEncomendas(cliente);
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


    private void cliqueHome() {
        appbarIconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(PerfilUsuarioActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

    private void cliqueSeta() {
        appbarIconSeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    PedidoAdapter.PedidoOnClickListener trataCliqueItem = new PedidoAdapter.PedidoOnClickListener() {
        @Override
        public void onClickPedido(View view, int position) {
            Encomenda minhaEncomenda = listaEncomendas.get(position);
            informacoesApp.setProdutoSelecionado(minhaEncomenda.getProduto());
            Intent it = new Intent(PerfilUsuarioActivity.this, ProdutoDetalhadoActivity.class);
            it.putExtra("pedidoClicado", minhaEncomenda);
            startActivity(it);
        }
    };
}