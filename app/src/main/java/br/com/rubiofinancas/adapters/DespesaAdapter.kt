package br.com.rubiofinancas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rubiofinancas.data.domain.Despesa
import br.com.rubiofinancas.databinding.DespesaAdapterBinding

class DespesaAdapter(private val lista: List<Despesa>) : RecyclerView.Adapter<DespesaAdapter.DespesaAdapterViewHolder>() {

    inner class DespesaAdapterViewHolder(val item: DespesaAdapterBinding) : RecyclerView.ViewHolder(item.root) {

        fun bind(despesa: Despesa) {




        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DespesaAdapterViewHolder {

        val itemBinding = DespesaAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DespesaAdapterViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: DespesaAdapterViewHolder, position: Int) {

        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

}










