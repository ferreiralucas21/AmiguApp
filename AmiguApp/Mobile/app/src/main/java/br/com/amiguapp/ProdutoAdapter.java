package br.com.amiguapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        Vendedor meuVendedor = null;

        for (int i = 0; i <= listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getIdVendedor() == meuProduto.getFkIdVendedor()) {
                meuVendedor = listaVendedores.get(i);
                break;
            }
        }

        holder.tvNomeProduto.setText(meuProduto.getNome());
        holder.tvPrecoProduto.setText(String.valueOf(meuProduto.getPreco()));
        holder.tvLojaNome.setText(meuVendedor.getNome());
//        holder.tvLojaNome.setText(vendedor(meuProduto));
        Bitmap bmp = BitmapFactory.decodeByteArray(meuProduto.getImagem(), 0, meuProduto.getImagem().length);
        holder.imgViewProduto.setImageBitmap(bmp);

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

//    private String vendedor(Produto meuProduto) {
//        String vendedorDoProduto = null;
//        for (int i = 0; i <= listaVendedores.size(); i++) {
//            if (listaVendedores.get(i).getIdVendedor() == meuProduto.getFkIdVendedor()) {
//                vendedorDoProduto = listaVendedores.get(i).getNome();
//                break;
//            }
//        }
//        return vendedorDoProduto;
//    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeProduto, tvPrecoProduto, tvLojaNome;
        ImageView imgViewProduto;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNomeProduto = itemView.findViewById(R.id.tvProdutoNome);
            tvPrecoProduto = itemView.findViewById(R.id.tvProdutoPreco);
            tvLojaNome = itemView.findViewById(R.id.tvProdutoLojaNome);
            imgViewProduto = itemView.findViewById(R.id.imageViewProduto);
        }
    }

    public interface ProdutoOnClickListener {
        public void onClickProduto(View view, int position);
    }

}
