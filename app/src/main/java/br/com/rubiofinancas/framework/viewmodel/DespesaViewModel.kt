package br.com.rubiofinancas.framework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.rubiofinancas.data.domain.Despesa
import br.com.rubiofinancas.data.repository.DespesaRepository
import kotlinx.coroutines.launch

class DespesaViewModel (application: Application): AndroidViewModel(application) {

    private val repository = DespesaRepository(application)
    val listAll = MutableLiveData<MutableList<Despesa>>()

    fun atualizarLista(){

        viewModelScope.launch {
            listAll.value = repository.getAll()
        }

    }

    fun adicionarDespesa(despesa: Despesa){

        viewModelScope.launch {
            repository.insert(despesa)
            atualizarLista()

        }

    }
    fun atualizarReceita(despesa: Despesa){

        viewModelScope.launch {
            repository.update(despesa)
            atualizarLista()

        }

    }

    fun deletarTodasReceitas(id:MutableList<Long>){

        viewModelScope.launch {

            repository.deleteAll(id)

        }


    }

}