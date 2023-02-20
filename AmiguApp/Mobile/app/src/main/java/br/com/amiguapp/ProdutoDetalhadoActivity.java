package br.com.amiguapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import controller.ConexaoSocketController;
import modelDominio.Cliente;
import modelDominio.Encomenda;
import modelDominio.Produto;

public class ProdutoDetalhadoActivity extends AppCompatActivity {
    TextView tvProdutoDetalhadoNomeLoja, tvProdutoDetalhadoNome, tvProdutoDetalhadoPreco, tvProdutoDetalhadoTamanho,
            tvProdutoDetalhadoDescricao, tvProdutoDetalhadoContato, tvProdutoDetalhadoRua, tvProdutoDetalhadoComplemento,
            tvProdutoDetalhadoBairro, tvProdutoDetalhadoCep;
    EditText etProdutoDetalhadoQuantidade;
    Button bAlterarEndereco, bProdutoFazerPedido;
    ImageView imgProdutoDetalhado, appbarIconSeta, appbarIconHome, appbarIconPerfil;
    RadioButton rbPagamentoPix, rbPagamentoCartao;

    SearchView searchView;
    InformacoesApp informacoesApp;
    String msgRecebida;
    private ArrayList<Encomenda> listaEncomendas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhado);
        searchView = findViewById(R.id.appbarSearchView);
        informacoesApp = (InformacoesApp) getApplicationContext();
        tvProdutoDetalhadoNomeLoja = findViewById(R.id.tvProdutoDetalhadoNomeLoja);
        tvProdutoDetalhadoNome = findViewById(R.id.tvProdutoDetalhadoNome);
        tvProdutoDetalhadoPreco = findViewById(R.id.tvProdutoDetalhadoPreco);
        tvProdutoDetalhadoTamanho = findViewById(R.id.tvProdutoDetalhadoTamanho);
        tvProdutoDetalhadoDescricao = findViewById(R.id.tvProdutoDetalhadoDescricao);
        tvProdutoDetalhadoContato = findViewById(R.id.tvProdutoDetalhadoContato);
        tvProdutoDetalhadoRua = findViewById(R.id.tvProdutoDetalhadoRua);
        tvProdutoDetalhadoComplemento = findViewById(R.id.tvProdutoDetalhadoComplemento);
        tvProdutoDetalhadoBairro = findViewById(R.id.tvProdutoDetalhadoBairro);
        tvProdutoDetalhadoCep = findViewById(R.id.tvProdutoDetalhadoCep);
        etProdutoDetalhadoQuantidade = findViewById(R.id.etProdutoDetalhadoQuantidade);
        bProdutoFazerPedido = findViewById(R.id.bProdutoFazerPedido);
        appbarIconPerfil = findViewById(R.id.appbarIconPerfil);
        bAlterarEndereco = findViewById(R.id.bAlterarEndereco);
        imgProdutoDetalhado = findViewById(R.id.imgProdutoDetalhado);
        appbarIconSeta = findViewById(R.id.appbarIconSeta);
        appbarIconHome = findViewById(R.id.appbarIconHome);
        rbPagamentoPix = findViewById(R.id.rbPagamentoPix);
        rbPagamentoCartao = findViewById(R.id.rbPagamentoCartao);

        CliqueSeta();
        CliquePerfil();
        CliqueAlterarEndereco();
        CliqueHome();

        Intent intent = getIntent();
        if (intent.hasExtra("produtoClicado")) {
            final Produto meuProduto = (Produto) intent.getSerializableExtra("produtoClicado");

            tvProdutoDetalhadoNomeLoja.setText(meuProduto.getVendedor().getNome());
            Bitmap bmp = BitmapFactory.decodeByteArray(meuProduto.getImagem(), 0, meuProduto.getImagem().length);
            imgProdutoDetalhado.setImageBitmap(bmp);
            tvProdutoDetalhadoNome.setText(meuProduto.getNome());
            tvProdutoDetalhadoPreco.setText(String.valueOf(meuProduto.getPreco()));
            tvProdutoDetalhadoTamanho.setText(String.valueOf(meuProduto.getTamanho()));
            tvProdutoDetalhadoDescricao.setText(meuProduto.getDescricao());
            tvProdutoDetalhadoContato.setText(meuProduto.getVendedor().getTelefone());
            if (!etProdutoDetalhadoQuantidade.getText().toString().equals("")) {
                etProdutoDetalhadoQuantidade.getText().toString();
            }
        }

        informacoesApp = (InformacoesApp) getApplicationContext();

        Cliente cliente = informacoesApp.getClienteLogado();
        tvProdutoDetalhadoRua.setText(cliente.getRua());
        tvProdutoDetalhadoBairro.setText(cliente.getBairro());
        tvProdutoDetalhadoComplemento.setText(cliente.getComplemento());
        tvProdutoDetalhadoCep.setText(cliente.getCep());

        bProdutoFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etProdutoDetalhadoQuantidade.getText().toString().equals("")) {
                    if (!tvProdutoDetalhadoRua.getText().toString().equals("") && !tvProdutoDetalhadoBairro.getText().toString().equals("")
                            && !tvProdutoDetalhadoCep.getText().toString().equals("")) {

                        int quantidade = Integer.parseInt(etProdutoDetalhadoQuantidade.getText().toString());
                        Produto produto = informacoesApp.getProdutoSelecionado();
                        Cliente cliente = informacoesApp.getClienteLogado();

                        Encomenda minhaEncomenda = new Encomenda(new Produto(produto.getIdProduto()), new Cliente(cliente.getIdCliente()), quantidade);

                        Thread thread1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                                msgRecebida = conexaoSocket.inserirEncomenda(minhaEncomenda);

                                if (minhaEncomenda != null) {
                                    Intent it = new Intent(ProdutoDetalhadoActivity.this, PerfilUsuarioActivity.class);
                                    startActivity(it);
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível realizar o pedido!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                        thread1.start();
                    } else {
                        AlertDialog.Builder enderecoVazio = new AlertDialog.Builder(ProdutoDetalhadoActivity.this);
                        enderecoVazio.setTitle("Atenção");
                        enderecoVazio.setMessage("Preencha todos os campos do endereço de entrega");
                        enderecoVazio.setNeutralButton("ok", null);
                        enderecoVazio.create().show();
                    }

                } else {
                    etProdutoDetalhadoQuantidade.setError("Informe a quantidade");
                    etProdutoDetalhadoQuantidade.requestFocus();
                }
            }
        });

    }

    private void CliqueHome() {
        appbarIconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProdutoDetalhadoActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

    private void CliqueAlterarEndereco() {
        bAlterarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProdutoDetalhadoActivity.this, PerfilUsuarioActivity.class);
                startActivity(it);
            }
        });
    }

    private void CliquePerfil() {
        appbarIconPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProdutoDetalhadoActivity.this, PerfilUsuarioActivity.class);
                startActivity(it);
            }
        });
    }

    private void CliqueSeta() {
        appbarIconSeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProdutoDetalhadoActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }
}