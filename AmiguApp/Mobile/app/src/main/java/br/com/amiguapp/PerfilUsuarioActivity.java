package br.com.amiguapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import controller.ConexaoSocketController;
import modelDominio.Cliente;
import modelDominio.Encomenda;
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
    Cliente clienteAlterado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        informacoesApp = (InformacoesApp) getApplicationContext();
        vinculaElementos();

        cliqueSeta();
        cliqueHome();

        Cliente cliente = informacoesApp.getClienteLogado();
        carregaUsuarioLogado(cliente);

        cliqueBotaoEditarDados(cliente);

        Thread thread = carregaListaDosPedidos(cliente);
        thread.start();
    }

    @NonNull
    private Thread carregaListaDosPedidos(Cliente cliente) {
        Thread thread = new Thread(() -> {
            ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
            listaEncomendas = conexaoSocket.listaEncomendas(cliente);
            if (listaEncomendas != null) {
                runOnUiThread(() -> {
                    pedidoAdapter = new PedidoAdapter(listaEncomendas, trataCliqueItem);
                    recyclerListaPedidos.setLayoutManager(new LinearLayoutManager(informacoesApp));
                    recyclerListaPedidos.setItemAnimator(new DefaultItemAnimator());
                    recyclerListaPedidos.setAdapter(pedidoAdapter);
                });

            } else {
                runOnUiThread(() -> Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível obter a lista dos pedidos!", Toast.LENGTH_SHORT).show());
            }
        });
        return thread;
    }

    private void cliqueBotaoEditarDados(Cliente cliente) {
        bPerfilUsuarioEditar.setOnClickListener(view -> {
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

                            Thread thread1 = efetuaEdicoes();
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
        });
    }

    @NonNull
    private Thread efetuaEdicoes() {
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
        return thread1;
    }

    private void carregaUsuarioLogado(Cliente cliente) {
        etPerfilUsuarioCpf.setText(cliente.getCpf());
        etPerfilUsuarioEmail.setText(cliente.getEmail());
        etPerfilUsuarioNome.setText(cliente.getNome());
        etPerfilUsuarioTelefone.setText(cliente.getTelefone());
        etPerfilUsuarioRua.setText(cliente.getRua());
        etPerfilUsuarioBairro.setText(cliente.getBairro());
        etPerfilUsuarioComplemento.setText(cliente.getComplemento());
        etPerfilUsuarioCep.setText(cliente.getCep());
    }

    private void vinculaElementos() {
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
    }


    private void cliqueHome() {
        appbarIconHome.setOnClickListener(view -> {
            Intent it = new Intent(PerfilUsuarioActivity.this, MainActivity.class);
            startActivity(it);
        });
    }

    private void cliqueSeta() {
        appbarIconSeta.setOnClickListener(view -> finish());
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