package com.example.reviewalimenticio.models

import android.widget.ImageButton


data class Refeicao(
    var nomeRefeicao: String = "",
    var notaRefeicao: Float = 0f,
    var nomeRestaurante: String = "",
    var comentario: String = "",
    var localizacaoButton: Boolean

) {
    fun getNomeRefeicao(): String {
        return nomeRefeicao
    }

    fun setNomeRefeicao(nomeRefeicao: String){
        this.nomeRefeicao = nomeRefeicao
    }

    fun getNotaRefeicao(): Float{
        return notaRefeicao
    }

    fun setNotaRefeicao(notaRefeicao: Float){
        this.notaRefeicao = notaRefeicao
    }

    fun getNomeRestaurante(): String {
        return nomeRestaurante
    }

    fun setNomeRestaurante(nomeRestaurante: String){
        this.nomeRefeicao = nomeRefeicao
    }

    fun getComentario(): String {
        return comentario
    }

    fun setComentario(comentario: String){
        this.comentario = comentario
    }

    fun getLocalizaButton(): Boolean{
        return localizacaoButton
    }

    fun setLocalizaButton(localizacaoButton: Boolean){
        this.localizacaoButton = localizacaoButton
    }
}
