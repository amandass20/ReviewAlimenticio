package com.example.reviewalimenticio

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewalimenticio.models.Refeicao
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class TelaPrincipal : AppCompatActivity() {
    lateinit var bt_adc_refeicao: ImageButton
    lateinit var listarecyclerview: RecyclerView
    private lateinit var refeicoesRef: CollectionReference
    private lateinit var refeicoesListener: ListenerRegistration
    private val listaRefeicoes:MutableList<Refeicao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_principal)

        IniciarComponentes()

        bt_adc_refeicao.setOnClickListener { v ->
            val intent = Intent(this, TelaAdicionarRefeicao::class.java)
            startActivity(intent)

        }
        val firestore = FirebaseFirestore.getInstance()
        refeicoesRef = firestore.collection("refeicoes")
    }

    override fun onStart() {
        super.onStart()
        refeicoesListener = refeicoesRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Toast.makeText(this, "Erro ao buscar os dados do Firestore.", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            listaRefeicoes.clear()

            if (snapshot != null && !snapshot.isEmpty) {
                for (document in snapshot) {
                    val nomeRefeicao = document.getString("nome") ?: ""
                    val notaRefeicao = document.getDouble("nota") ?: 0.0

                    val refeicao = Refeicao(
                        nomeRefeicao = nomeRefeicao,
                        notaRefeicao = notaRefeicao.toFloat()
                    )

                    listaRefeicoes.add(refeicao)
                }
                atualizarListaRefeicoes()
            }
        }
    }


    override fun onStop() {
        super.onStop()
        refeicoesListener.remove()
    }

    private fun atualizarListaRefeicoes() {
        val adapter = RefeicaoAdapter(listaRefeicoes)

        listarecyclerview.adapter = adapter
        listarecyclerview.layoutManager =   LinearLayoutManager(this)
    }


    private fun IniciarComponentes(){
        bt_adc_refeicao = findViewById(R.id.bt_adc_refeicao)
        listarecyclerview = findViewById(R.id.listareciclerview)
    }
}