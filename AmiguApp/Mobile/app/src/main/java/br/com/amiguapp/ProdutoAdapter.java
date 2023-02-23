package br.com.amiguapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

        bind(holder, meuProduto);
    }

    private void bind(MyViewHolder holder, Produto meuProduto) {
        holder.tvNomeProduto.setText(meuProduto.getNome());
        holder.tvPrecoProduto.setText(String.valueOf(PrecoUtil.precoFormat(meuProduto.getPreco())));
        holder.tvLojaNome.setText(meuProduto.getVendedor().getNome());
        Bitmap bmp = BitmapFactory.decodeByteArray(meuProduto.getImagem(), 0, meuProduto.getImagem().length);
        holder.imgViewProduto.setImageBitmap(bmp);

        // clique no produto
        if (produtoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    produtoOnClickListener.onClickProduto(holder.itemView, holder.getAdapterPosition());
                }
            });
        }
    }

    public void search(List<Produto> listaFiltrada){
        listaProdutos = listaFiltrada;
        notifyDataSetChanged();
    }

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
