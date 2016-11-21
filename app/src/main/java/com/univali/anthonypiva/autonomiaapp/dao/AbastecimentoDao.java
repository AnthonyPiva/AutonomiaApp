package com.univali.anthonypiva.autonomiaapp.dao;

import com.univali.anthonypiva.autonomiaapp.modelo.Abastecimento;

import java.util.ArrayList;

/**
 * Created by 5725089 on 18/11/2016.
 */
public class AbastecimentoDao {

    private static ArrayList<Abastecimento> listaAbts;


    private static void inicializarLista(){
        if(AbastecimentoDao.listaAbts == null){
            AbastecimentoDao.listaAbts = new ArrayList<>();
        }
    }

    public static void salvar(Abastecimento ab){
        inicializarLista();
        listaAbts.add(ab);
    }

    public static ArrayList<Abastecimento> obterLista(){
        inicializarLista();
        return listaAbts;
    }

}
