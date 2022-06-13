package br.edu.qi.appprojetoproduto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {
    private EditText edtNome;
    private EditText edtCategoria;
    private EditText edtValor;
    private Button btnCadastrar;
    private ProdutoDAO objProdutoDAO;
    private Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        edtNome = findViewById(R.id.edtNome);
        edtCategoria= findViewById(R.id.edtCategoria);
        edtValor = findViewById(R.id.edtValor);
        btnCadastrar = findViewById(R.id.btncCadastrar);
        btnListar = findViewById(R.id.btnListar);
        objProdutoDAO = new ProdutoDAO(this);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto objProduto = new Produto();
                objProduto.setNome(edtNome.getText().toString());
                objProduto.setCategoria(edtCategoria.getText().toString());
                objProduto.setValor(Float.parseFloat(edtValor.getText().toString()));
                objProdutoDAO.cadastrarProduto(objProduto);
                edtNome.setText("");
                edtCategoria.setText("");
                edtValor.setText("");
                edtNome.requestFocus();
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });



    }
}