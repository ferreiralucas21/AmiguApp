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

import java.util.ArrayList;
import java.util.List;

import modelDominio.Encomenda;
import modelDominio.Produto;
import modelDominio.Vendedor;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MyViewHolder> {
    private List<Encomenda> listaEncomendas;
    private List<Produto> listaProdutos;
    private ProdutoOnClickListener produtoOnClickListener;

    public PedidoAdapter(List<Encomenda> listaEncomendas, ProdutoOnClickListener produtoOnClickListener) {
        this.listaEncomendas = listaEncomendas;
        this.produtoOnClickListener = produtoOnClickListener;
    }

    @Override
    public PedidoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_pedidos, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PedidoAdapter.MyViewHolder holder, final int position) {
        Encomenda minhaEncomenda = listaEncomendas.get(position);

        holder.tvPedidoNome.setText(minhaEncomenda.getProduto().getNome());
        holder.tvPedidoSituacao.setText(minhaEncomenda.getStatus());
        holder.tvPedidoPreco.setText(String.valueOf(minhaEncomenda.getProduto().getPreco()));
        holder.tvPedidoNomeLoja.setText(minhaEncomenda.getVendedor().getNome());
        Bitmap bmp = BitmapFactory.decodeByteArray(minhaEncomenda.getProduto().getImagem(), 0, minhaEncomenda.getProduto().getImagem().length);
        holder.imgViewPedido.setImageBitmap(bmp);

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

    @Override
    public int getItemCount() {
        return listaEncomendas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPedidoNome, tvPedidoPreco, tvPedidoSituacao, tvPedidoNomeLoja;
        ImageView imgViewPedido;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvPedidoNome = itemView.findViewById(R.id.tvPedidoNome);
            tvPedidoPreco = itemView.findViewById(R.id.tvPedidoPreco);
            tvPedidoSituacao = itemView.findViewById(R.id.tvPedidoSituacao);
            tvPedidoNomeLoja = itemView.findViewById(R.id.tvPedidoNomeLoja);
            imgViewPedido = itemView.findViewById(R.id.imgViewPedido);
        }
    }

    public interface ProdutoOnClickListener {
        public void onClickProduto(View view, int position);
    }

}
