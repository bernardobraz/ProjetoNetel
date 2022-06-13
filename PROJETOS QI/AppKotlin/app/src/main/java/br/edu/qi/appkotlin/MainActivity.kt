package br.edu.qi.appkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_rodar.setOnClickListener {
            val mensagem = edtMensagem.text.toString()
            edtMensagem.setText("")
            txtInput.text = mensagem
        }


    }


}