package br.com.amiguapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import controller.ConexaoSocketController;
import modelDominio.Cliente;
import modelDominio.Encomenda;
import modelDominio.Produto;
import modelDominio.Vendedor;

public class ProdutoDetalhadoActivity extends AppCompatActivity {
    TextView tvProdutoDetalhadoNomeLoja, tvProdutoDetalhadoNome, tvProdutoDetalhadoPreco, tvProdutoDetalhadoTamanho,
            tvProdutoDetalhadoDescricao, tvProdutoDetalhadoContato, tvProdutoDetalhadoRua, tvProdutoDetalhadoComplemento,
            tvProdutoDetalhadoBairo, tvProdutoDetalhadoCep;
    EditText etProdutoDetalhadoQuantidade;
    Button bAlterar, bProdutoFazerPedido;
    ImageView imgProdutoDetalhado, appbarIconSeta, appbarIconHome;
    RadioButton rbPagamentoPix, rbPagamentoCartao;

    SearchView searchView;
    InformacoesApp informacoesApp;
    String msgRecebida;
    Encomenda encomenda;
    int fkIdProduto, fkIdCliente;
    ArrayList<Produto> listaProdutos;
    ArrayList<Vendedor> listaVendedores;
    Context context;


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
        tvProdutoDetalhadoBairo = findViewById(R.id.tvProdutoDetalhadoBairo);
        tvProdutoDetalhadoCep = findViewById(R.id.tvProdutoDetalhadoCep);
        etProdutoDetalhadoQuantidade = findViewById(R.id.etProdutoDetalhadoQuantidade);
        bProdutoFazerPedido = findViewById(R.id.bProdutoFazerPedido);
        bAlterar = findViewById(R.id.bAlterar);
        imgProdutoDetalhado = findViewById(R.id.imgProdutoDetalhado);
        appbarIconSeta = findViewById(R.id.appbarIconSeta);
        appbarIconHome = findViewById(R.id.appbarIconHome);
        rbPagamentoPix = findViewById(R.id.rbPagamentoPix);
        rbPagamentoCartao = findViewById(R.id.rbPagamentoCartao);

        appbarIconSeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProdutoDetalhadoActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        appbarIconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProdutoDetalhadoActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("produtoClicado") &&(intent.hasExtra("vendedorDoProduto"))) {
            final Produto meuProduto = (Produto) intent.getSerializableExtra("produtoClicado");
            final Vendedor meuVendedor = (Vendedor) intent.getSerializableExtra("vendedorDoProduto");

            tvProdutoDetalhadoNomeLoja.setText(meuVendedor.getNome());
            Bitmap bmp = BitmapFactory.decodeByteArray(meuProduto.getImagem(),0, meuProduto.getImagem().length);
            imgProdutoDetalhado.setImageBitmap(bmp);
            tvProdutoDetalhadoNome.setText(meuProduto.getNome());
            tvProdutoDetalhadoPreco.setText(String.valueOf(meuProduto.getPreco()));
            tvProdutoDetalhadoTamanho.setText(String.valueOf(meuProduto.getTamanho()));
            tvProdutoDetalhadoDescricao.setText(meuProduto.getDescricao());
            tvProdutoDetalhadoContato.setText(meuVendedor.getTelefone());
            if (!etProdutoDetalhadoQuantidade.getText().toString().equals("")){
                etProdutoDetalhadoQuantidade.getText().toString();
            }
        }

        informacoesApp = (InformacoesApp) getApplicationContext();

        bProdutoFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etProdutoDetalhadoQuantidade.getText().toString().equals("")){

                    int quantidade = Integer.parseInt(etProdutoDetalhadoQuantidade.getText().toString());
                    int fkIdProduto = informacoesApp.getProdutoSelecionado().getIdProduto();
                    int fkIdCliente = informacoesApp.getClienteLogado().getIdCliente();
                    System.out.println("ID do cliente logado "+ fkIdCliente);

                    encomenda = new Encomenda(quantidade,new Produto(fkIdProduto),new Cliente(fkIdCliente));

                    Thread thread1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                            msgRecebida = conexaoSocket.inserirEncomenda(encomenda);

                            if(encomenda != null){
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
                    etProdutoDetalhadoQuantidade.setError("Informe a quantidade");
                    etProdutoDetalhadoQuantidade.requestFocus();
                }
            }
        });

    }
}