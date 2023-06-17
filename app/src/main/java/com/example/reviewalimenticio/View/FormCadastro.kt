package com.example.reviewalimenticio

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.reviewalimenticio.viewModel.FormCadastroViewModel

class FormCadastro : AppCompatActivity() {
    private lateinit var edit_nome: EditText
    private lateinit var edit_email: EditText
    private lateinit var edit_senha: EditText
    lateinit var bt_cadastrar: Button
    private val viewModel: FormCadastroViewModel by lazy { ViewModelProvider(this).get(
        FormCadastroViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)

        supportActionBar?.hide()
        IniciarComponentes()

        bt_cadastrar.setOnClickListener { v ->
            val nome = edit_nome.text.toString()
            val email = edit_email.text.toString()
            val senha = edit_senha.text.toString()

            viewModel.cadastrarUsuario(nome, email, senha, v)
            viewModel.salvarDadosUsuario(nome)
        }
    }

    private fun IniciarComponentes(){
        edit_nome = findViewById(R.id.edit_nome)
        edit_email = findViewById(R.id.edit_email)
        edit_senha = findViewById(R.id.edit_senha)
        bt_cadastrar = findViewById(R.id.bt_cadastrar)
    }
}