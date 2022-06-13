package com.example.projgravacarnoite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {
    private EditText edtMarca;
    private EditText edtModelo;
    private EditText edtKilometragem;
    private EditText edtAno;
    private EditText edtCor;
    private EditText edtCombustivel;
    private EditText edtCambio;
    private EditText edtPreco;
    private Button btnAnunciar;
    private Button btnLista;
    private CarroDAO objCarroDAO;
    private Carro objCarro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        getSupportActionBar().hide();

        edtMarca = findViewById(R.id.edtMarca);
        edtModelo = findViewById(R.id.edtModelo);
        edtKilometragem = findViewById(R.id.edtKilometragem);
        edtAno = findViewById(R.id.edtAno);
        edtCor = findViewById(R.id.edtCor);
        edtCombustivel = findViewById(R.id.edtCombustivel);
        edtCambio = findViewById(R.id.edtCambio);
        edtPreco = findViewById(R.id.edtPreco);
        btnAnunciar= findViewById(R.id.btnAnunciar);
        btnLista = findViewById(R.id.btnLista);
        objCarroDAO = new CarroDAO(this);

        Intent i = getIntent();
        if (i.hasExtra("carro")){
            objCarro = (Carro) i.getSerializableExtra("carro");
            edtMarca.setText(objCarro.getMarca());
            edtModelo.setText(objCarro.getModelo());
            edtKilometragem.setText(String.valueOf(objCarro.getKilometragem()));
            edtAno.setText(String.valueOf(objCarro.getAno()));
            edtCor.setText(objCarro.getCor());
            edtCombustivel.setText(objCarro.getCombustivel());
            edtCambio.setText(objCarro.getCambio());
            edtPreco.setText(String.valueOf(objCarro.getPreco()));
        }

        btnAnunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (objCarro == null){
                    Carro objCarro = new Carro();
                    objCarro.setMarca(edtMarca.getText().toString());
                    objCarro.setModelo(edtModelo.getText().toString());
                    objCarro.setKilometragem(Integer.parseInt(edtKilometragem.getText().toString()));
                    objCarro.setAno(Integer.parseInt(edtAno.getText().toString()));
                    objCarro.setCor(edtCor.getText().toString());
                    objCarro.setCombustivel(edtCombustivel.getText().toString());
                    objCarro.setCambio(edtCambio.getText().toString());
                    objCarro.setPreco(Float.parseFloat(edtPreco.getText().toString()));


                    objCarroDAO.anunciarCarroBD(objCarro);
                    Toast.makeText(FormularioActivity.this, "Veículo Anunciado com Sucesso!", Toast.LENGTH_LONG).show();
                }else {
                    objCarro.setMarca(edtMarca.getText().toString());
                    objCarro.setModelo(edtModelo.getText().toString());
                    objCarro.setKilometragem(Integer.parseInt(edtKilometragem.getText().toString()));
                    objCarro.setAno(Integer.parseInt(edtAno.getText().toString()));
                    objCarro.setCor(edtCor.getText().toString());
                    objCarro.setCombustivel(edtCombustivel.getText().toString());
                    objCarro.setCambio(edtCambio.getText().toString());
                    objCarro.setPreco(Float.parseFloat(edtPreco.getText().toString()));
                    objCarroDAO.alterarCarroBD(objCarro);
                    Toast.makeText(FormularioActivity.this, "Alterado com Sucesso!", Toast.LENGTH_LONG).show();
                }
                edtMarca.setText("");
                edtModelo.setText("");
                edtKilometragem.setText("");
                edtAno.setText("");
                edtCor.setText("");
                edtCombustivel.setText("");
                edtCambio.setText("");
                edtPreco.setText("");
                edtMarca.requestFocus();
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormularioActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });
    }


}
