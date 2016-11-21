package com.univali.anthonypiva.autonomiaapp.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by 5725089 on 18/11/2016.
 */
public class Abastecimento implements Parcelable{

    private int kmAtual;
    private int LitrosAbastecidos;
    private String dataDoAbastecimento;
    private String PostoDoAbastecimento;

    public Abastecimento(){

    }

    protected Abastecimento(Parcel in) {
        setKmAtual(in.readInt());
        setLitrosAbastecidos(in.readInt());
        setPostoDoAbastecimento(in.readString());
        setDataDoAbastecimento(in.readString());
    }

    public static final Creator<Abastecimento> CREATOR = new Creator<Abastecimento>() {
        @Override
        public Abastecimento createFromParcel(Parcel in) {
            return new Abastecimento(in);
        }

        @Override
        public Abastecimento[] newArray(int size) {
            return new Abastecimento[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getKmAtual());
        parcel.writeInt(getLitrosAbastecidos());
        parcel.writeString(getPostoDoAbastecimento());
        parcel.writeString(getDataDoAbastecimento());
    }

    public int getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(int kmAtual) {
        this.kmAtual = kmAtual;
    }

    public int getLitrosAbastecidos() {
        return LitrosAbastecidos;
    }

    public void setLitrosAbastecidos(int litrosAbastecidos) {
        LitrosAbastecidos = litrosAbastecidos;
    }

    public String getDataDoAbastecimento() {
        return dataDoAbastecimento;
    }

    public void setDataDoAbastecimento(String dataDoAbastecimento) {
        this.dataDoAbastecimento = dataDoAbastecimento;
    }

    public String getPostoDoAbastecimento() {
        return PostoDoAbastecimento;
    }

    public void setPostoDoAbastecimento(String postoDoAbastecimento) {
        PostoDoAbastecimento = postoDoAbastecimento;
    }

}
