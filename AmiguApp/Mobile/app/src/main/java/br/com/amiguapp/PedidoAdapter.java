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

import modelDominio.Encomenda;
import modelDominio.Produto;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MyViewHolder> {
    private List<Encomenda> listaEncomendas;
    private PedidoOnClickListener pedidoOnClickListener;

    public PedidoAdapter(List<Encomenda> listaEncomendas, PedidoOnClickListener pedidoOnClickListener) {
        this.listaEncomendas = listaEncomendas;
        this.pedidoOnClickListener = pedidoOnClickListener;
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
        holder.tvPedidoSituacao.append(minhaEncomenda.getStatus());
        holder.tvPedidoPreco.setText(String.valueOf(PrecoUtil.precoFormat(minhaEncomenda.getProduto().getPreco())));
        holder.tvPedidoQuantidade.append(String.valueOf(minhaEncomenda.getQuantidade()));
        holder.tvPedidoNomeLoja.setText(minhaEncomenda.getVendedor().getNome());
        Bitmap bmp = BitmapFactory.decodeByteArray(minhaEncomenda.getProduto().getImagem(), 0, minhaEncomenda.getProduto().getImagem().length);
        holder.imgViewPedido.setImageBitmap(bmp);

        // clique no pedido
        if (pedidoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pedidoOnClickListener.onClickPedido(holder.itemView, holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaEncomendas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPedidoNome, tvPedidoPreco, tvPedidoSituacao, tvPedidoQuantidade, tvPedidoNomeLoja;
        ImageView imgViewPedido;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvPedidoNome = itemView.findViewById(R.id.tvPedidoNome);
            tvPedidoPreco = itemView.findViewById(R.id.tvPedidoPreco);
            tvPedidoSituacao = itemView.findViewById(R.id.tvPedidoSituacao);
            tvPedidoQuantidade = itemView.findViewById(R.id.tvPedidoQuantidade);
            tvPedidoNomeLoja = itemView.findViewById(R.id.tvPedidoNomeLoja);
            imgViewPedido = itemView.findViewById(R.id.imgViewPedido);
        }
    }

    public interface PedidoOnClickListener {
        public void onClickPedido(View view, int position);
    }

}
