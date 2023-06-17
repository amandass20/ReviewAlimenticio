package com.example.reviewalimenticio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.reviewalimenticio.viewModel.FormLoginViewModel
import com.google.firebase.auth.FirebaseAuth

class FormLogin : AppCompatActivity() {
    private lateinit var text_tela_cadastro: TextView
    private lateinit var edit_email: EditText
    private lateinit var edit_senha: EditText
    private lateinit var bt_entrar: Button
    private lateinit var progressbar: ProgressBar
    private lateinit var viewModel: FormLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)

        supportActionBar?.hide()
        IniciarComponentes()

        text_tela_cadastro.setOnClickListener{
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }

        bt_entrar.setOnClickListener { v ->
            val email = edit_email.text.toString()
            val senha = edit_senha.text.toString()
            viewModel.loginUsuario(email, senha, progressbar, v, applicationContext)

        }

    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null){
            telaPrincipal()
        }
    }

    private fun telaPrincipal() {
        val intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }

    private fun IniciarComponentes(){
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro)
        edit_email = findViewById(R.id.edit_email)
        edit_senha = findViewById(R.id.edit_senha)
        bt_entrar = findViewById(R.id.bt_entrar)
        progressbar = findViewById(R.id.progressbar)

    }
}