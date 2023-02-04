package br.com.amiguapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import modelDominio.Produto;
import modelDominio.Vendedor;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {
    private List<Produto> listaProdutos;
    private ProdutoOnClickListener produtoOnClickListener;
    private List<Vendedor> listaVendedores;

    public ProdutoAdapter(List<Produto> listaProdutos, ProdutoOnClickListener produtoOnClickListener, List<Vendedor> listaVendedores) {
        this.listaProdutos = listaProdutos;
        this.produtoOnClickListener = produtoOnClickListener;
        this.listaVendedores = listaVendedores;
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
        holder.tvNomeProduto.setText(meuProduto.getNome());
        holder.tvPrecoProduto.setText(String.valueOf(meuProduto.getPreco()));
        holder.tvLojaNome.setText(vendedor(meuProduto));

        // clique no item do cliente
        if (produtoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    produtoOnClickListener.onClickProduto(holder.itemView, holder.getAdapterPosition());
                }
            });
        }
    }

    private String vendedor(Produto meuProduto) {
        String vendedorDoProduto = null;
        for (int i = 0; i <= listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getIdVendedor() == meuProduto.getFkIdVendedor()) {
                vendedorDoProduto = listaVendedores.get(i).getNome();
                break;
            }
        }
        return vendedorDoProduto;
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeProduto, tvPrecoProduto, tvLojaNome, imageViewProduto;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNomeProduto = itemView.findViewById(R.id.tvProdutoNome);
            tvPrecoProduto = itemView.findViewById(R.id.tvPrecoProduto);
            tvLojaNome = itemView.findViewById(R.id.tvLojaNome);
            imageViewProduto = itemView.findViewById(R.id.imageViewProduto);
        }
    }

    public interface ProdutoOnClickListener {
        public void onClickProduto(View view, int position);
    }

}
