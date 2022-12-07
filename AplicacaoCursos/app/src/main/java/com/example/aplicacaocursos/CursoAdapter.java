package com.example.aplicacaocursos;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;


public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.MyViewHolder> {
    private List<Curso> listaCursos;
    private CursoOnClickListener cursoOnClickListener;

    public CursoAdapter(List<Curso> listaCursos, CursoOnClickListener cursoOnClickListener) {
        this.listaCursos = listaCursos;
        this.cursoOnClickListener = cursoOnClickListener;
    }

    @Override
    public CursoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CursoAdapter.MyViewHolder holder, final int position) {
        Curso meuCurso = listaCursos.get(position);
        holder.tvCursoNome.setText(meuCurso.getNome());
        holder.tvCursoTipo.setText(meuCurso.getTipoLiteral());
        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado, deve ser feita a conversão com o String.valueOf() */

        // clique no item do cliente
        if (cursoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cursoOnClickListener.onClickCurso(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCursoNome, tvCursoTipo;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvCursoNome = (TextView) itemView.findViewById(R.id.tvCursoNome);
            tvCursoTipo = (TextView) itemView.findViewById(R.id.tvCursoTipo);


        }
    }

    public interface CursoOnClickListener {
        public void onClickCurso(View view, int position);
    }

}
