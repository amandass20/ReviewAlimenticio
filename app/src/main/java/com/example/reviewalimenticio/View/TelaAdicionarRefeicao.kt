package com.example.reviewalimenticio

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.reviewalimenticio.models.Refeicao
import com.example.reviewalimenticio.viewModel.TelaAdicionarRefeicaoViewModel
import com.google.firebase.firestore.FirebaseFirestore

class TelaAdicionarRefeicao : AppCompatActivity() {
    private lateinit var viewModel: TelaAdicionarRefeicaoViewModel
    lateinit var obterLocalizacao: Switch
    lateinit var localizacaoButton: ImageButton
    lateinit var et_nomeRefeicao: EditText
    lateinit var et_nomeRestaurante: EditText
    lateinit var et_comentario: EditText
    private lateinit var rb_notaRefeicao: RatingBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_refeicao)

        IniciarComponentes()

        viewModel.setupContext(this)

        obterLocalizacao.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                localizacaoButton.isEnabled = true
            } else {
                localizacaoButton.isEnabled = false
                viewModel.stopLocationUpdates()
            }
        }
        localizacaoButton.setOnClickListener {
            obterLocalizacaoAtual()
        }
    }


    fun obterLocalizacaoAtual() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.startLocationUpdates()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    fun onAdicionarClick(view: View) {
        val nomeRefeicao = et_nomeRefeicao.text.toString()
        val notaRefeicao = rb_notaRefeicao.rating
        val nomeRestaurante = et_nomeRestaurante.text.toString()
        val comentario = et_comentario.text.toString()
        val localizacaoButton = obterLocalizacao.isChecked

        val refeicao = Refeicao(
            nomeRefeicao = nomeRefeicao,
            notaRefeicao = notaRefeicao,
            nomeRestaurante = nomeRestaurante,
            comentario = comentario,
            localizacaoButton = localizacaoButton
        )
        val firestore = FirebaseFirestore.getInstance()
        val refeicaoRef = firestore.collection("refeicoes")
        refeicaoRef.add(refeicao)
            .addOnSuccessListener {
                Toast.makeText(this, "Refeição adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao adicionar a refeição. Tente novamente.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun IniciarComponentes(){
        obterLocalizacao = findViewById(R.id.obterLocalizacao)
        localizacaoButton = findViewById(R.id.localizacaoButton)
        et_nomeRefeicao = findViewById(R.id.et_nomeRefeicao)
        et_nomeRestaurante = findViewById(R.id.et_nomeRestaurante)
        et_comentario = findViewById(R.id.et_comentario)
        rb_notaRefeicao = findViewById(R.id.rb_notaRefeicao)

    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1001
    }

}