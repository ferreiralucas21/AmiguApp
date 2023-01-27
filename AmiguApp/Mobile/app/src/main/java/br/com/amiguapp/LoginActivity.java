package br.com.amiguapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import controller.ConexaoSocketController;
import modelDominio.Cliente;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginEmail, etLoginSenha;
    Button bLoginEntrar, bLoginCadastrar;

    Cliente cliente;
    String msgRecebida;

    InformacoesApp informacoesApp;

    Boolean resultadoConexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginSenha = findViewById(R.id.etLoginSenha);
        bLoginEntrar = findViewById(R.id.bLoginEntrar);
        bLoginCadastrar = findViewById(R.id.bLoginCadastrar);

        informacoesApp = (InformacoesApp) getApplicationContext();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                resultadoConexao = conexaoSocket.criaConexao();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (resultadoConexao == true) {
                            Toast.makeText(informacoesApp, "Conexão estabelecida com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(informacoesApp, "Erro: não foi possível estabelexer a conexão com o servidor.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        thread.start();

        bLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etLoginEmail.getText().toString().equals("")) {
                    if (!etLoginSenha.getText().toString().equals("")) {
                        String email = etLoginEmail.getText().toString();
                        String senha = etLoginSenha.getText().toString();

                        cliente = new Cliente(email, senha);

                        Thread thread1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                                cliente = conexaoSocket.efetuarLogin(cliente);

                                if (cliente != null) {
                                    // sugiro que o servidor retorne o usuário com todas as informações do banco. Esse objeto pode estar na InformacoesApp e ser usado em futuras necessidades
                                    informacoesApp.setClienteLogado(cliente);

                                    Intent it = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(it);
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(informacoesApp, "ATENÇÃO: Usuário e senha não conferem!", Toast.LENGTH_SHORT).show();
                                            limpaCampos();
                                        }
                                    });
                                }

                            }
                        });
                        thread1.start();

                    } else {
                        etLoginSenha.setError("Informe a senha");
                        etLoginSenha.requestFocus();
                    }
                } else {
                    etLoginEmail.setError("Informe o email");
                    etLoginEmail.requestFocus();
                }
            }
        });

    }

    public void limpaCampos() {
        etLoginEmail.setText("");
        etLoginSenha.setText("");
    }
}