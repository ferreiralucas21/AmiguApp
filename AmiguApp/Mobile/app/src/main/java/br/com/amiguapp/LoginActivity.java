package br.com.amiguapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import controller.ConexaoSocketController;
import modelDominio.Cliente;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginEmail, etLoginSenha;
    Button bLoginEntrar, bLoginCadastrar;

    Cliente cliente;
    InformacoesApp informacoesApp;
    Boolean resultadoConexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        informacoesApp = (InformacoesApp) getApplicationContext();
        vinculaElementos();

        cliqueBotaoCadastrarNovaConta();

        Thread thread = estabeleceConexao();
        thread.start();

        cliqueBotaoLogin();

    }

    private void cliqueBotaoLogin() {
        bLoginEntrar.setOnClickListener(view -> {
            if (!etLoginEmail.getText().toString().equals("")) {
                if (!etLoginSenha.getText().toString().equals("")) {
                    String email = etLoginEmail.getText().toString();
                    String senha = etLoginSenha.getText().toString();

                    cliente = new Cliente(email, senha);

                    Thread thread1 = efetuaLogin();
                    thread1.start();

                } else {
                    etLoginSenha.setError("Informe a senha");
                    etLoginSenha.requestFocus();
                }
            } else {
                etLoginEmail.setError("Informe o email");
                etLoginEmail.requestFocus();
            }
        });
    }

    @NonNull
    private Thread efetuaLogin() {
        Thread thread1 = new Thread(() -> {
            ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
            cliente = conexaoSocket.efetuarLogin(cliente);

            if (cliente != null) {
                informacoesApp.setClienteLogado(cliente);

                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(it);
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(informacoesApp, "ATENÇÃO: Usuário e senha não conferem!", Toast.LENGTH_SHORT).show();
                    limpaCampos();
                });
            }

        });
        return thread1;
    }

    @NonNull
    private Thread estabeleceConexao() {
        Thread thread = new Thread(() -> {
            ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
            resultadoConexao = conexaoSocket.criaConexao();
            runOnUiThread(() -> {
                if (!resultadoConexao == true) {
                    Toast.makeText(informacoesApp, "Erro: não foi possível estabelexer a conexão com o servidor.", Toast.LENGTH_SHORT).show();
                }
            });
        });
        return thread;
    }

    private void cliqueBotaoCadastrarNovaConta() {
        bLoginCadastrar.setOnClickListener(view -> {
            Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(it);
        });
    }

    private void vinculaElementos() {
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginSenha = findViewById(R.id.etLoginSenha);
        bLoginEntrar = findViewById(R.id.bLoginEntrar);
        bLoginCadastrar = findViewById(R.id.bLoginCadastrar);
    }

    public void limpaCampos() {
        etLoginEmail.setText("");
        etLoginSenha.setText("");
    }
}