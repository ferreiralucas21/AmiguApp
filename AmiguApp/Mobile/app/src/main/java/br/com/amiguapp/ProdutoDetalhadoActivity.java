package br.com.amiguapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProdutoDetalhadoActivity extends AppCompatActivity {
    TextView tvProdutoNomeLoja, tvLojaNome;
    ImageView imageProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhado);
    }
}