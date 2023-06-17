package com.example.reviewalimenticio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewalimenticio.models.Refeicao

class RefeicaoAdapter(private val listaRefeicoes: MutableList<Refeicao>) : RecyclerView.Adapter<RefeicaoAdapter.RefeicaoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RefeicaoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return RefeicaoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RefeicaoViewHolder, position: Int) {
        val refeicao = listaRefeicoes[position]
        holder.bind(refeicao)
    }

    override fun getItemCount(): Int {
        return listaRefeicoes.size
    }

    inner class RefeicaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(refeicao: Refeicao) {
            val textView = itemView.findViewById<TextView>(android.R.id.text1)
            textView.text =refeicao.nomeRefeicao
        }
    }
}
