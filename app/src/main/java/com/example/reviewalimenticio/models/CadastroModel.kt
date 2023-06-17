package com.example.reviewalimenticio.models

import android.util.Log
import androidx.annotation.NonNull
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.OnFailureListener

class CadastroModel {
    fun salvarDadosUsuario(nome: String) {
        val db = FirebaseFirestore.getInstance()

        val usuarios: MutableMap<String, Any> = HashMap()
        usuarios["nome"] = nome

        val usuarioID = FirebaseAuth.getInstance().currentUser?.uid

        if (usuarioID != null) {
            val documentReference = db.collection("Usuarios").document(usuarioID)

            documentReference.set(usuarios)
                .addOnSuccessListener {
                    Log.d("db", "Sucesso ao salvar os dados")
                }
                .addOnFailureListener { e ->
                    Log.d("db_error", "Erro ao salvar os dados: ${e.toString()}")
                }
        }
    }
}