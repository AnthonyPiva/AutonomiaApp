package com.univali.anthonypiva.autonomiaapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.univali.anthonypiva.autonomiaapp.R;
import com.univali.anthonypiva.autonomiaapp.modelo.Abastecimento;

import java.util.ArrayList;

/**
 * Created by 5725089 on 18/11/2016.
 */
public class AbastecimentoAdapterRecycle extends RecyclerView.Adapter {

    private ArrayList<Abastecimento> listaAbts;
    private Context context;

    public AbastecimentoAdapterRecycle(Context c, ArrayList<Abastecimento> p) {
        this.listaAbts = p;
        this.context = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_abastecimentos_card, parent, false);
        AbastecimentoViewHolder retorno = new AbastecimentoViewHolder(view);
        return retorno;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AbastecimentoViewHolder caixinha = (AbastecimentoViewHolder) holder;
        Abastecimento a = listaAbts.get(position);
        caixinha.seAtualiza(a);
    }

    @Override
    public int getItemCount() {
        return this.listaAbts.size();
    }

    public class AbastecimentoViewHolder extends RecyclerView.ViewHolder {

        private ImageView Iconeposto;
        private TextView litros;
        private TextView data;
        private TextView Km;

        public AbastecimentoViewHolder(View itemView) {
            super(itemView);

            this.Iconeposto = (ImageView) itemView.findViewById(R.id.Iconeposto);
            this.litros = (TextView) itemView.findViewById(R.id.litros);
            this.Km = (TextView) itemView.findViewById(R.id.Km);
            this.data = (TextView) itemView.findViewById(R.id.data);

        }

        public void seAtualiza(Abastecimento davez) {
            Km.setText("Km: "+String.valueOf(davez.getKmAtual()));
            litros.setText("Litros: "+String.valueOf(davez.getLitrosAbastecidos()));
            data.setText(davez.getDataDoAbastecimento());
            if(davez.getPostoDoAbastecimento().equals("Texaco")){
                Iconeposto.setImageResource(R.drawable.texaco);
            }else if(davez.getPostoDoAbastecimento().equals("Shell")){
                Iconeposto.setImageResource(R.drawable.shell);
            }else if(davez.getPostoDoAbastecimento().equals("Petrobras")){
                Iconeposto.setImageResource(R.drawable.petrobras);
            }else{
                Iconeposto.setImageResource(R.drawable.ipiranga);
            }
            //Iconeposto.setImageResource(R.drawable.Iconeposto);

        }

    }
}