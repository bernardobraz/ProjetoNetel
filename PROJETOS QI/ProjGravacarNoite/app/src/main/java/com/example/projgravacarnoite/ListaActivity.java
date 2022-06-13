package com.example.projgravacarnoite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private ListView lstCarros;
    private CarroDAO objcarroDAO;
    private SearchView icConsultar;
    private List<Carro> todosOsCarros;
    private List<Carro> carrosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lstCarros = findViewById(R.id.lstCarros);
        objcarroDAO = new CarroDAO(this);

        todosOsCarros = objcarroDAO.consultarCarroBD();
        carrosFiltrados.addAll(todosOsCarros);

        ArrayAdapter<Carro> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,carrosFiltrados);
        lstCarros.setAdapter(adaptador);

        registerForContextMenu(lstCarros);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_superior,menu);
        icConsultar = (SearchView) menu.findItem(R.id.ic_Consultar).getActionView();

        icConsultar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procurarCarrosPorMarca(s);
                return false;
            }
        });

        return true;
    }

    public void procurarCarrosPorMarca(String marca){
        carrosFiltrados.clear();
        for (int i = 0;i < todosOsCarros.size();i++){
            if (todosOsCarros.get(i).getMarca().toLowerCase().contains(marca.toLowerCase())){
                carrosFiltrados.add(todosOsCarros.get(i));
            }
        }
        lstCarros.invalidateViews();
    }

    public void abrirTeladeAnunciar(MenuItem item){
        Intent i = new Intent(ListaActivity.this,FormularioActivity.class);
        startActivity(i);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_item,menu);
    }

    public void excluirCarro(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Carro objCarroDeletado = carrosFiltrados.get(menuInfo.position);

        AlertDialog confirmacao = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_search_view)
                .setTitle("Atenção")
                .setMessage("Deseja realmente excluir esse anuncio?")
                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        todosOsCarros.remove(objCarroDeletado);
                        carrosFiltrados.remove(objCarroDeletado);
                        objcarroDAO.excluirCarroBD(objCarroDeletado);
                        lstCarros.invalidateViews();
                    }
                }).create();
        confirmacao.show();
    }

    public void alterarProduto(MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Carro objProdutoAletarado = carrosFiltrados.get(menuInfo.position);

        Intent i = new Intent(this, FormularioActivity.class);
        i.putExtra("carro",objProdutoAletarado);
        startActivity(i);
    }



}