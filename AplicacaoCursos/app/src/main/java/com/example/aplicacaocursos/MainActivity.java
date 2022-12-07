package com.example.aplicacaocursos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etMainUsuario, etMainSenha;
    Button bMainEntrar, bMainCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMainUsuario = findViewById(R.id.etMainUsuario);
        etMainSenha = findViewById(R.id.etMainSenha);
        bMainEntrar = findViewById(R.id.bMainEntrar);
        bMainCancelar = findViewById(R.id.bMainCancelar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bMainEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verificando se o usuário informou os dados
                if (!etMainUsuario.getText().toString().equals("")) {
                    if (!etMainSenha.getText().toString().equals("")) {
                        //obtendo as informações
                        String usuario = etMainUsuario.getText().toString();
                        String senha = etMainSenha.getText().toString();

                        //verificando se o usuario e senha estão corretos
                        if (usuario.equals("lucas") && senha.equals("12345")) {
                            //criando a intent para chamada a nota tela
                            Intent it = new Intent(MainActivity.this, MenuActivity.class);
                            //adicionando os parâmetros na Intent
                            it.putExtra("usuario", usuario);
                            it.putExtra("senha", senha);
                            //enviando a solicitação ao sistema operacional

                            startActivity(it);
                        } else {
                            Toast.makeText(MainActivity.this, "Erro: usuário e senha não conferem!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        etMainSenha.setError("Erro: informe a senha.");
                        etMainSenha.requestFocus();
                    }
                } else {
                    etMainUsuario.setError("Erro: informe o usuário.");
                    etMainUsuario.requestFocus();
                }
            }
        });

        bMainCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void limpaCampos(){
        etMainUsuario.setText("");
        etMainSenha.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
