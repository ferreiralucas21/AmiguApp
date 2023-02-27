package br.com.amiguapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import controller.ConexaoSocketController;
import modelDominio.Cliente;

public class CadastroActivity extends AppCompatActivity {

    EditText etCadastroNome, etCadastroEmail, etCadastroTelefone, etCadastroSenha, etCadastroCpf;
    Button bCadastroCadastrar;
    ImageView appbarCadastroSeta;

    Cliente cliente;
    String msgRecebida;
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        informacoesApp = (InformacoesApp) getApplicationContext();
        vinculaElementos();

        cliqueSeta();
        cliqueBotaoCadastrar();

    }

    private void cliqueBotaoCadastrar() {
        bCadastroCadastrar.setOnClickListener(view -> {
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

                                Thread thread1 = efetuaCadastro();
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
        });
    }

    @NonNull
    private Thread efetuaCadastro() {
        Thread thread1 = new Thread(() -> {
            ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
            msgRecebida = conexaoSocket.inserirCliente(cliente);
            Log.i("novoCliente", "novo cliente: " + cliente.getNome());

            if(cliente != null){
                informacoesApp.setClienteInserido();

                Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(it);
            } else {

                runOnUiThread(() -> Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível realizar o cadastro!", Toast.LENGTH_SHORT).show());
            }
        });
        return thread1;
    }

    private void vinculaElementos() {
        etCadastroNome = findViewById(R.id.etCadastroNome);
        etCadastroEmail = findViewById(R.id.etCadastroEmail);
        etCadastroTelefone = findViewById(R.id.etCadastroTelefone);
        etCadastroSenha = findViewById(R.id.etCadastroSenha);
        etCadastroCpf = findViewById(R.id.etCadastroCpf);
        bCadastroCadastrar = findViewById(R.id.bCadastroCadastrar);
        appbarCadastroSeta = findViewById(R.id.appbarCadastroSeta);
    }

    private void cliqueSeta() {
        appbarCadastroSeta.setOnClickListener(view -> {
            Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
            startActivity(it);
        });
    }
}