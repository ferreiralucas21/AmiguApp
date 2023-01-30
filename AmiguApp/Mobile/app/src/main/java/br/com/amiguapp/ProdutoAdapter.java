package br.com.amiguapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import modelDominio.Produto;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private final List<Produto> listaProdutos;
    private final Context context;

    public ProdutoAdapter(Context context, List<Produto> listaProdutos) {
        this.context = context;
        this.listaProdutos = listaProdutos;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.list_item_produtos, parent, false);
        return new ProdutoViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder holder, int position) {
        Produto produto = listaProdutos.get(position);
        holder.vincula(produto);
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    class ProdutoViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeLoja;
        private final TextView nomeProduto;
        private final TextView precoProduto;

        public ProdutoViewHolder(View itemView) {
            super(itemView);
            nomeLoja = itemView.findViewById(R.id.tvNomeLoja);
            nomeProduto = itemView.findViewById(R.id.tvNomeProduto);
            precoProduto = itemView.findViewById(R.id.tvPrecoProduto);
        }

        public void vincula(Produto produto) {
            preencheCampo(produto);
        }

        private void preencheCampo(Produto produto) {
            nomeLoja.setText(produto.getFkIdVendedor());
            nomeProduto.setText(produto.getNome());
            precoProduto.setText((int) produto.getPreco());
        }
    }

}