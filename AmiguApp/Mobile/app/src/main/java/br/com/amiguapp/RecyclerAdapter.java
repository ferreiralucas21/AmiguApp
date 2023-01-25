package br.com.amiguapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter {

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
                    .inflate(R.layout.item_list_row, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ProdutoAdapter.MyViewHolder holder, final int position) {
            Produto meuProduto = listaProdutos.get(position);
            holder.tvProdutoNome.setText(meuProduto.getNome());
            holder.tvProdutoPreco.setText("Preço: R$ " + meuProduto.getPreco());
            /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado (sem concatenação), deve ser feita a conversão com o String.valueOf() */

            // clique no item do cliente
            if (produtoOnClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        produtoOnClickListener.onClickProduto(holder.itemView, position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return listaProdutos.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvProdutoNome, tvProdutoPreco;

            public MyViewHolder(View itemView) {
                super(itemView);
                tvProdutoNome = (TextView) itemView.findViewById(R.id.tvProdutoNome);
                tvProdutoPreco = (TextView) itemView.findViewById(R.id.tvProdutoPreco);


            }
        }

        public interface ProdutoOnClickListener {
            public void onClickProduto(View view, int position);
        }
    }
