package br.com.amiguapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import modelDominio.Produto;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {
    private List<Produto> listaProdutos;
    private ProdutoOnClickListener produtoOnClickListener;

    public ProdutoAdapter(List<Produto> listaProdutos, ProdutoOnClickListener produtoOnClickListener) {
        this.listaProdutos = listaProdutos;
        this.produtoOnClickListener = produtoOnClickListener;
    }

    @Override
    public ProdutoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_produtos, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProdutoAdapter.MyViewHolder holder, final int position) {
        Produto meuProduto = listaProdutos.get(position);
        holder.tvNomeLoja.setText(meuProduto.getFkIdVendedor());
        holder.tvNomeProduto.setText(meuProduto.getNome());
        holder.tvPrecoProduto.setText(String.valueOf(meuProduto.getPreco()));

        // clique no item do cliente
        if (produtoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    produtoOnClickListener.onClickProduto(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeLoja, tvNomeProduto, tvPrecoProduto;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvNomeLoja = itemView.findViewById(R.id.tvNomeLoja);
            tvNomeProduto = itemView.findViewById(R.id.tvNomeProduto);
            tvPrecoProduto = itemView.findViewById(R.id.tvPrecoProduto);
        }
    }

    public interface ProdutoOnClickListener {
        public void onClickProduto(View view, int position);
    }

}
