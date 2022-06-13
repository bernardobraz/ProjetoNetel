package com.example.projgravacarnoite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoBD extends SQLiteOpenHelper {
    private static final String name = "bd_revenda";
    private static final int version = 1;

    public ConexaoBD(@Nullable Context context){
        super(context, name,null,version);

    }
    @Override
    public void onCreate(SQLiteDatabase bd_revenda){
        bd_revenda.execSQL("CREATE TABLE tb_carro(id Integer not null primary key autoincrement," +
                "marca varchar(30)not null,modelo varchar (30), ano integer(10), kilometragem integer(10),cor varchar(30),combustivel varchar(10),cambio varchar(12),preco float(7,2))");
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }

}
