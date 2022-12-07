package com.example.aplicacaocursos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {
    //Declaração de componentes/elementos
    EditText etCadastroNome, etCadastroCargaHoraria, etCadastroConceito;
    Button bCadastroSalvar, bCadastroCancelar;
    RadioButton rbCadastroTecnologo, rbCadastroBacharelado, rbCadastroLicenciatura;

    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        //Vinculando Elementos
        etCadastroNome = findViewById(R.id.etCadastroNome);
        etCadastroCargaHoraria = findViewById(R.id.etCadastroCargaHoraria);
        etCadastroConceito = findViewById(R.id.etCadastroConceito);
        rbCadastroTecnologo = findViewById(R.id.rbCadastroTecnologo);
        rbCadastroBacharelado = findViewById(R.id.rbCadastroBacharelado);
        rbCadastroLicenciatura = findViewById(R.id.rbCadastroLicenciatura);
        bCadastroSalvar = findViewById(R.id.bCadastroSalvar);
        bCadastroCancelar = findViewById(R.id.bcadastroCancelar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //obtendo contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        bCadastroSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verificando se o usuário informou os dados
                if (!etCadastroNome.getText().toString().equals("")){
                    if (!etCadastroCargaHoraria.getText().toString().equals("")){
                        if (!etCadastroConceito.getText().toString().equals("")){
                            if (rbCadastroTecnologo.isChecked() || rbCadastroBacharelado.isChecked() || rbCadastroLicenciatura.isChecked()){
                                //obtendo as informações
                                String nome = etCadastroNome.getText().toString();
                                float cargaHoraria = Float.parseFloat(etCadastroCargaHoraria.getText().toString());
                                int conceito = Integer.parseInt(etCadastroConceito.getText().toString());
                                int tipo = 0;
                                if (rbCadastroTecnologo.isChecked()) {
                                    tipo = 1;
                                } else if (rbCadastroBacharelado.isChecked()){
                                    tipo = 2;
                                } else if (rbCadastroLicenciatura.isChecked()){
                                    tipo = 3;
                                }

                                //criando o objeto da classe
                                Curso meuCurso = new Curso(nome, cargaHoraria, conceito, tipo);

                                //adicionando o objeto na lista
                                informacoesApp.getListaCursos().add(meuCurso);

                                Toast.makeText(informacoesApp, "Curso cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                                limpaCampos();

                            } else {
                                Toast.makeText(CadastroActivity.this, "Erro: informe o tipo de curso.", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            etCadastroConceito.setError("Erro: informe o conceito.");
                            etCadastroConceito.requestFocus();
                        }

                    } else {
                        etCadastroCargaHoraria.setError("Erro: informe a carga horária.");
                        etCadastroCargaHoraria.requestFocus();
                    }

                } else {
                    etCadastroNome.setError("Erro: informe o nome.");
                    etCadastroNome.requestFocus();
                }
            }
        });

        bCadastroCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });

        }
    public void limpaCampos() {
        etCadastroNome.setText("");
        etCadastroCargaHoraria.setText("");
        etCadastroConceito.setText("");
        rbCadastroBacharelado.setChecked(false);
        rbCadastroLicenciatura.setChecked(false);
        rbCadastroTecnologo.setChecked(false);
    }

}
