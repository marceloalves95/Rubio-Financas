package br.com.rubiofinancas.presenter.adapters

import android.util.SparseBooleanArray
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.rubiofinancas.R
import br.com.rubiofinancas.data.domain.Receita
import br.com.rubiofinancas.databinding.ReceitaAdapterBinding
import br.com.rubiofinancas.presenter.ui.receita.ReceitaArgs
import br.com.rubiofinancas.presenter.ui.receita.ReceitaFragment
import br.com.rubiofinancas.presenter.ui.receita.ReceitaFragmentDirections
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Rubio Finanças
 * @author Marcelo Alves
 * 15/03/2021
 */


class ReceitaAdapter(val receita: MutableList<Receita>, val receitaFragment:ReceitaFragment) : RecyclerView.Adapter<ReceitaAdapter.ReceitaViewHolder>() {

    val selectedItems = SparseBooleanArray()
    private var currentSelectedPos = -1

    inner class ReceitaViewHolder(private val itemBinding: ReceitaAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(receita: Receita) {

            val receitaMes = initDatas(receita.data_hoje)
            val receitaValor = "R$ ${receita.valor}"
            itemBinding.textViewItem.text = receita.item
            itemBinding.textViewCategoria.text = receita.categoria
            itemBinding.textViewValor.text = receitaValor
            itemBinding.textViewDataHoje.text = receitaMes
            itemBinding.cardView.setOnLongClickListener {

                onItemLongClick?.invoke(adapterPosition)


                return@setOnLongClickListener true


            }

            itemBinding.cardView.setOnClickListener {

                if (selectedItems.isNotEmpty()) onItemClick?.invoke(adapterPosition)

                receitaFragment.actionMode?.finish()


                argumentosReceita(receita)

            }

            if (currentSelectedPos == adapterPosition) currentSelectedPos = -1


            itemBinding.cardView.isChecked = receita.selected

            if (itemBinding.cardView.isChecked) itemBinding.cardView.strokeColor = ContextCompat.getColor(receitaFragment.requireContext(), R.color.purple_200)
            else itemBinding.cardView.strokeColor = ContextCompat.getColor(receitaFragment.requireContext(), R.color.white)








        }

        fun initDatas(data: String): String {

            val local = Locale("pt", "BR")
            val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", local)
            val localDate = LocalDate.parse(data, dtf)

            val dia = localDate.dayOfMonth.toString()
            val mes = localDate.month

            val mesReduzido = DateTimeFormatter.ofPattern("MMM", local)
            val mesCompleto = mesReduzido.format(mes)

            return "$dia de $mesCompleto"


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {

        val itemBinding = ReceitaAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReceitaViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {

        holder.bind(receita[position])


    }

    override fun getItemCount(): Int = receita.size

    var onItemClick: ((Int) -> Unit)? = null
    var onItemLongClick: ((Int) -> Unit)? = null

    fun toggleSelection(position: Int) {

        currentSelectedPos = position
        if (selectedItems[position, false]) {
            selectedItems.delete(position)

            receita[position].selected = false

        } else {
            selectedItems.put(position, true)
            receita[position].selected = true
        }

        notifyItemChanged(position)


    }

    fun deletarReceitas(){

        receita.removeAll(receita.filter { it.selected })

        notifyDataSetChanged()
        currentSelectedPos = -1


    }

    private fun argumentosReceita(receita: Receita){

        val receitaArgs = ReceitaArgs(receita.data_hoje, receita.item, receita.categoria, receita.valor, receita.mes,receita.ano)
        val id = receita.id
        val action = ReceitaFragmentDirections.actionNavReceitaToCadastroReceita(receitaArgs, id)

        receitaFragment.findNavController().navigate(action)


    }


}