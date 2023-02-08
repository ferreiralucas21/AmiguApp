package br.com.amiguapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import controller.ConexaoSocketController;
import modelDominio.Cliente;

public class CadastroActivity extends AppCompatActivity {

    EditText etCadastroNome, etCadastroEmail, etCadastroTelefone, etCadastroSenha, etCadastroCpf;
    Button bCadastroCadastrar;
    ImageView appbarCadastroSeta;

    Cliente cliente;
    String msgRecebida;
    InformacoesApp informacoesApp;
    Boolean resultadoConexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        etCadastroNome = findViewById(R.id.etCadastroNome);
        etCadastroEmail = findViewById(R.id.etCadastroEmail);
        etCadastroTelefone = findViewById(R.id.etCadastroTelefone);
        etCadastroSenha = findViewById(R.id.etCadastroSenha);
        etCadastroCpf = findViewById(R.id.etCadastroCpf);
        bCadastroCadastrar = findViewById(R.id.bCadastroCadastrar);
        appbarCadastroSeta = findViewById(R.id.appbarCadastroSeta);

        appbarCadastroSeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
//                resultadoConexao = conexaoSocket.criaConexao();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (resultadoConexao == true) {
//                            Toast.makeText(informacoesApp, "Conexão estabelecida com sucesso!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(informacoesApp, "Erro: não foi possível estabelexer a conexão com o servidor.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        });
//        thread.start();

        informacoesApp = (InformacoesApp) getApplicationContext();

        bCadastroCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etCadastroNome.getText().toString().equals("")) {
                    if (!etCadastroEmail.getText().toString().equals("")) {
                        if (!etCadastroTelefone.getText().toString().equals("")) {
                            if (!etCadastroSenha.getText().toString().equals("")) {
                                if (!etCadastroCpf.getText().toString().equals("")) {

                                    String nome = etCadastroNome.getText().toString();
                                    String email = etCadastroEmail.getText().toString();
                                    String telefone = etCadastroTelefone.getText().toString();
                                    String senha = etCadastroSenha.getText().toString();
                                    String cpf = etCadastroCpf.getText().toString();

                                    cliente = new Cliente(nome, email, telefone, senha, cpf);

                                    Thread thread1 = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                                            cliente = conexaoSocket.inserirCliente(cliente); //para aqui
                                            Log.i("novoCliente", "novo cliente: " + cliente.getNome());

                                            if(cliente != null){
                                                // sugiro que o servidor retorne o usuário com todas as informações do banco. Esse objeto pode estar na InformacoesApp e ser usado em futuras necessidades
                                                informacoesApp.setClienteInserido(cliente);

                                                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                                                startActivity(it);
                                            } else {

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível realizar o cadastro!", Toast.LENGTH_SHORT).show();
                                                        //limpaCampos();
                                                    }
                                                });
                                            }
                                        }
                                    });
                                    thread1.start();

                                } else {
                                    etCadastroCpf.setError("Informe o CPF");
                                    etCadastroCpf.requestFocus();
                                }
                            } else {
                                etCadastroSenha.setError("Informe a senha");
                                etCadastroSenha.requestFocus();
                            }
                        } else {
                            etCadastroTelefone.setError("Informe o telefone");
                            etCadastroTelefone.requestFocus();
                        }
                    } else {
                        etCadastroEmail.setError("Informe o email");
                        etCadastroEmail.requestFocus();
                    }
                } else {
                    etCadastroNome.setError("Informe o nome");
                    etCadastroNome.requestFocus();
                }
            }
        });
    }
}