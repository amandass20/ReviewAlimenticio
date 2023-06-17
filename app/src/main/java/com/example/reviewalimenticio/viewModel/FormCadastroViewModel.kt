package com.example.reviewalimenticio.viewModel

import android.graphics.Color
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.reviewalimenticio.models.CadastroModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class FormCadastroViewModel : ViewModel() {
    val mensagens: Array<String> = arrayOf("Preencha todos os campos", "Cadastro realizado com sucesso")
    private val cadastroModel: CadastroModel = CadastroModel()

    fun cadastrarUsuario(nome: String, email: String, senha: String, bt_cadastrar: View){

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {

            val snackbar = Snackbar.make(bt_cadastrar, mensagens[0], Snackbar.LENGTH_SHORT)
            snackbar.setBackgroundTint(Color.WHITE)
            snackbar.setTextColor(Color.BLACK)
            snackbar.show()

        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener{ task ->
                if (task.isSuccessful){

                    val snackbar = Snackbar.make(bt_cadastrar, mensagens[1], Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()
                } else {
                    val erro: String
                    try {
                        throw task.exception!!
                    } catch (e:FirebaseAuthWeakPasswordException){
                        erro = "Digite uma senha com no mínimo 6 caracteres"
                    } catch (e:FirebaseAuthUserCollisionException){
                        erro = "Esta conta já foi cadastrada"
                    } catch (e:FirebaseAuthInvalidCredentialsException){
                        erro = "E-mail inválido"
                    } catch (e:Exception){
                        erro = "Erro ao cadastrar usuário"
                    }
                    val snackbar = Snackbar.make(bt_cadastrar, erro, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()
                }
            }
        }
    }

    fun salvarDadosUsuario(nome: String){
        cadastroModel.salvarDadosUsuario(nome)
    }
}