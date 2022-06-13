package br.edu.qi.appprojetoproduto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListaActivity extends AppCompatActivity {
    private ListView lstProduto;
    private ProdutoDAO objprodutoDAO;
    private SearchView icConsultar;
    private List<Produto> todosOsProdutos;
    private List<Produto> produtosFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lstProduto = findViewById(R.id.lstProdutos);
        objprodutoDAO = new ProdutoDAO(this);

        todosOsProdutos = objprodutoDAO.consultarProdutos();
        produtosFiltrados.addAll(todosOsProdutos);

        ArrayAdapter<Produto> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,produtosFiltrados);
        lstProduto.setAdapter(adaptador);

        lstProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Produto produtoSelecionado = (Produto) adaptador.getItem(posicao);

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(ListaActivity.this);

                janelaDeEscolha.setTitle("Escolha");
                janelaDeEscolha.setMessage("O que deseja fazer com o Produto selecionado?");

                janelaDeEscolha.setNeutralButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                janelaDeEscolha.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                janelaDeEscolha.create().show();

            }
        });


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
                procurarProdutosPorNome(s);
                return false;
            }
        });

        return true;
    }

    public void procurarProdutosPorNome(String nome){
        produtosFiltrados.clear();
        for (int i = 0;i < todosOsProdutos.size();i++){
            if (todosOsProdutos.get(i).getNome().toLowerCase().contains(nome.toLowerCase())){
                produtosFiltrados.add(todosOsProdutos.get(i));
            }
        }
        lstProduto.invalidateViews();
    }

    public void abrirTelaDeCadastro(MenuItem item){
        Intent i = new Intent(ListaActivity.this,CadastroActivity.class);
        startActivity(i);
    }



}