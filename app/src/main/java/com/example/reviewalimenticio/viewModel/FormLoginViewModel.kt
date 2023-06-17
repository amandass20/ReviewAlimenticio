package com.example.reviewalimenticio.viewModel

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import com.example.reviewalimenticio.TelaPrincipal
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.reviewalimenticio.FormLogin

@Suppress("DEPRECATION")
class FormLoginViewModel {
    val mensagens: Array<String> = arrayOf("Preencha todos os campos")

    fun loginUsuario(email: String, senha: String, progressbar: ProgressBar, bt_entrar: View, context: Context){

        if (email.isEmpty() || senha.isEmpty()) {

            val snackbar = Snackbar.make(bt_entrar, mensagens[0], Snackbar.LENGTH_SHORT)
            snackbar.setBackgroundTint(Color.WHITE)
            snackbar.setTextColor(Color.BLACK)
            snackbar.show()

        } else {
            autenticarUsuario(email, senha, progressbar, bt_entrar, context)
        }
    }

    private fun autenticarUsuario(email: String, senha: String, progressbar: ProgressBar, bt_entrar: View, context: Context){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener{ task ->
            if (task.isSuccessful){
                progressbar.visibility = View.VISIBLE
                Handler().postDelayed({
                    telaPrincipal(context)

                }, 3000)
            } else {
                val erro: String
                try {
                    throw task.exception!!
                } catch (e:Exception){
                    erro = "Erro ao logar usuário"
                }
                val snackbar = Snackbar.make(bt_entrar, erro, Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.WHITE)
                snackbar.setTextColor(Color.BLACK)
                snackbar.show()
            }
        }
    }

    private fun telaPrincipal(context: Context) {
        val intent = Intent(context, TelaPrincipal::class.java)
        context.startActivity(intent)
        (context as AppCompatActivity).finish() //talvez eu tire
    }

}