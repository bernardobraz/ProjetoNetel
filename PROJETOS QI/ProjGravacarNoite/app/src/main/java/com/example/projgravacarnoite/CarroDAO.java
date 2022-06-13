package com.example.projgravacarnoite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    private SQLiteDatabase bd_revenda;
    private ConexaoBD conexaoBD;
    public ArrayList<Integer> arrayIds;

    public CarroDAO(Context context){
        this.conexaoBD = new ConexaoBD(context);
        this.bd_revenda = conexaoBD.getWritableDatabase();
    }
    private ContentValues gerarValores(Carro objCarro){

        ContentValues values = new ContentValues();
        values.put("marca",objCarro.getMarca());
        values.put("modelo",objCarro.getModelo());
        values.put("ano",objCarro.getAno());
        values.put("kilometragem",objCarro.getKilometragem());
        values.put("cor",objCarro.getCor());
        values.put("preco",objCarro.getPreco());
        values.put("combustivel",objCarro.getCombustivel());
        values.put("cambio",objCarro.getCambio());

        return values;
    }

    public void anunciarCarroBD(Carro objCarro){
        this.bd_revenda.insert("tb_carro", null, this.gerarValores(objCarro));

    }
    public List<Carro> consultarCarroBD(){
        List<Carro> listaDeCarros = new ArrayList<>();
        arrayIds = new ArrayList<>();

        Cursor cursor = this.bd_revenda.query("tb_carro",new String[]{"id", "marca", "modelo", "kilometragem", "ano", "cor", "combustivel", "cambio", "preco"},
                null,null,null,null,null);
        while (cursor.moveToNext()){
            arrayIds.add(cursor.getInt(0));
            Carro objCarro = new Carro();
            objCarro.setId(cursor.getInt(0));
            objCarro.setMarca(cursor.getString(1));
            objCarro.setModelo(cursor.getString(2));
            objCarro.setAno(cursor.getInt(3));
            objCarro.setKilometragem(cursor.getInt(4));
            objCarro.setCor(cursor.getString(5));
            objCarro.setPreco(cursor.getFloat(6));
            objCarro.setCombustivel(cursor.getString(7));
            objCarro.setCambio(cursor.getString(8));
            listaDeCarros.add(objCarro);
        }
        return listaDeCarros;
    }
    public  void excluirCarroBD(Carro objCarro){
        this.bd_revenda.delete("tb_carro","id = ?",new String[]{String.valueOf(objCarro.getId())});
    }
    public void alterarCarroBD(Carro objCarro){
        this.bd_revenda.update("tb_carro",this.gerarValores(objCarro),"id = ?",new String[]{String.valueOf(objCarro.getId())});
    }
}
