package com.univali.anthonypiva.autonomiaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.univali.anthonypiva.autonomiaapp.dao.AbastecimentoDao;
import com.univali.anthonypiva.autonomiaapp.modelo.Abastecimento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 5725089 on 18/11/2016.
 */
public class CadastraAbastecimento extends Activity {

    private Button botaoConfima;
    private EditText kmatual;
    private EditText litrosabastecidos;
    private EditText dataab;
    private Spinner postodoab;
    private Date data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrarabastecimento);

        botaoConfima = (Button) findViewById(R.id.botaoConfirma);
        kmatual = (EditText) findViewById(R.id.kmatual);
        litrosabastecidos = (EditText) findViewById(R.id.litrosabastecidos);
        dataab = (EditText) findViewById(R.id.dataab);
        postodoab = (Spinner) findViewById(R.id.postodoab);


        final Calendar aux = Calendar.getInstance();
        data=new Date();
        data.setTime(aux.getTimeInMillis());
        data();
    }

    public void data(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dataab.setText(format.format(data));
    }

    private boolean CadastroValido(){
        ArrayList<Abastecimento> lista = AbastecimentoDao.obterLista();
        Abastecimento aux;
        if(kmatual.getText().toString().trim().equals("")){
            return false;
        }else{
            try{
                Integer.parseInt(kmatual.getText().toString());
            }catch (Exception e){
                return false;
            }
        }
        if(litrosabastecidos.getText().toString().trim().equals("")){
            return false;
        }else{
            try{
                Integer.parseInt(litrosabastecidos.getText().toString());
            }catch (Exception e){
                return false;
            }
        }
        if(lista.size()>0){
            aux=lista.get(lista.size()-1);
            if(Integer.parseInt(kmatual.getText().toString())< aux.getKmAtual()){
                return false;
            }
        }
        if(dataab.getText().toString().trim().equals("")) {
            return false;
        }
        return true;
    }

    private boolean DataCorreta() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date dataet = sdf.parse(dataab.getText().toString());
        Date dataagr = new Date();

        if(dataet.after(dataagr)){
            return false;
        }
        return true;
    }

    public void clicouConfirmar(View confirmamesmo) throws ParseException{
        if(!CadastroValido()){
            String msgerro = getResources().getString(R.string.Cadastro_invalido);
            Toast.makeText(this.getApplicationContext(), msgerro, Toast.LENGTH_SHORT).show();
            return;
        }
        if(!DataCorreta()){
            String msgerro = getResources().getString(R.string.Data_invalida);
            Toast.makeText(this.getApplicationContext(), msgerro, Toast.LENGTH_SHORT).show();
            return;
        }
        Abastecimento a = new Abastecimento();
        a.setKmAtual(Integer.parseInt(kmatual.getText().toString()));
        a.setLitrosAbastecidos(Integer.parseInt(litrosabastecidos.getText().toString()));
        a.setDataDoAbastecimento(dataab.getText().toString().trim());
        a.setPostoDoAbastecimento(postodoab.getSelectedItem().toString());

        AbastecimentoDao.salvar(a);
        String mensagemInternacionalizada = getResources().getString(R.string.Salvo_com_sucesso);
        Toast.makeText(this.getApplicationContext(), mensagemInternacionalizada, Toast.LENGTH_SHORT).show();
        Intent abridor = new Intent(this.getApplicationContext(), MainActivity.class);
        abridor.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(abridor);
        finish();


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
