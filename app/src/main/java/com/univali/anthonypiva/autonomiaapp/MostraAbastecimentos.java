package com.univali.anthonypiva.autonomiaapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.univali.anthonypiva.autonomiaapp.Adapter.AbastecimentoAdapterRecycle;
import com.univali.anthonypiva.autonomiaapp.dao.AbastecimentoDao;
import com.univali.anthonypiva.autonomiaapp.modelo.Abastecimento;

import java.util.ArrayList;

/**
 * Created by 5725089 on 18/11/2016.
 */
public class MostraAbastecimentos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostra_abastecimentos);

        ArrayList<Abastecimento> lista = AbastecimentoDao.obterLista();

        RecyclerView Mostraabs = (RecyclerView) findViewById(R.id.Mostraabs);

        RecyclerView.LayoutManager formaApresentacao = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL,false);

        Mostraabs.setLayoutManager(formaApresentacao);
        AbastecimentoAdapterRecycle adaptador = new AbastecimentoAdapterRecycle(this.getApplicationContext(), lista);
        Mostraabs.setAdapter(adaptador);

    }

}
