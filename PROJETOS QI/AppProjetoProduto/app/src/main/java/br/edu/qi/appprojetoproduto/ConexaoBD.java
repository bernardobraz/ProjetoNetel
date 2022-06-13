package br.edu.qi.appprojetoproduto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoBD extends SQLiteOpenHelper {
    private static final String name = "bd_mercado";
    private static final int version = 1;


    public ConexaoBD(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {

        bd.execSQL("create table tb_produto(id Integer not null primary key autoincrement," +
                "nome varchar(30) not null, categoria varchar(30), valor float(7,2))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

