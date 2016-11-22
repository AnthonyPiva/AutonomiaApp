package com.univali.anthonypiva.autonomiaapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.univali.anthonypiva.autonomiaapp.dao.AbastecimentoDao;
import com.univali.anthonypiva.autonomiaapp.modelo.Abastecimento;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Button BotaAddAbastecimento;
    private Button BotaoVisualizarAbastecimentos;
    private TextView autonomiaAtual;
    private TextView kmLitro;
    private TextView unidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BotaAddAbastecimento = (Button) findViewById(R.id.BotaAddAbastecimento);
        BotaoVisualizarAbastecimentos = (Button) findViewById(R.id.BotaoVisualizarAbastecimentos);
        autonomiaAtual = (TextView) findViewById(R.id.autonomiaAtual);
        kmLitro = (TextView) findViewById(R.id.kmLitro);
        unidade = (TextView) findViewById(R.id.unidade);

        if(AbastecimentoDao.obterLista().size()<2){
            kmLitro.setText("0");
        }else{
            ArrayList<Abastecimento> lista = AbastecimentoDao.obterLista();
            float kmt=lista.get(lista.size()-1).getKmAtual()-lista.get(0).getKmAtual();
            float totallitros=0;
            for(int i=0;i<lista.size()-1;i++){
                totallitros+=lista.get(i).getLitrosAbastecidos();
            }
            float autonomia=kmt/totallitros;
            String autonomiaa = String.format("%.2f",autonomia);
            kmLitro.setText(autonomiaa);
        }
    }

    public void ClicouNoBotaoAddAbastecimento(View QuemClicou){
        Intent abridor = new Intent(this.getApplicationContext(), CadastraAbastecimento.class);
        startActivity(abridor);
    }

    public void ClicouNoBotaoVisualizarAbastecimentos(View QuemClicou){
        if(AbastecimentoDao.obterLista().isEmpty()){
            Toast.makeText(this.getApplicationContext(), "Não há elementos para exibir", Toast.LENGTH_SHORT).show();

        }else {
            Intent abridor = new Intent(this.getApplicationContext(), MostraAbastecimentos.class);
            startActivity(abridor);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
