package br.com.rubiofinancas.framework.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.rubiofinancas.data.domain.Receita
import br.com.rubiofinancas.data.repository.ReceitaRepository
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Rubio Finanças
 *
 * @author Marcelo Alves
 * 15/03/2021
 */
class ReceitaViewModel(application: Application):AndroidViewModel(application){

    private val repository = ReceitaRepository(application)
    val listAll = MutableLiveData<MutableList<Receita>>()
    val maxValor = MutableLiveData<Double>()
    val minValor = MutableLiveData<Double>()
    val mediaValor = MutableLiveData<Double>()


    fun atualizarLista(){

        viewModelScope.launch {
            listAll.value = repository.getAll()
        }

    }

    fun maiorValor():LiveData<Double>{


        viewModelScope.launch {

            maxValor.value = repository.getMaxValor()

        }

        return maxValor

    }
    fun menorValor():LiveData<Double>{


        viewModelScope.launch {

            minValor.value = repository.getMinValor()

        }

        return minValor

    }
    fun mediaValor():LiveData<Double>{


        viewModelScope.launch {


                mediaValor.value = repository.getMedia()

        }

        return mediaValor

    }

    fun adicionarReceita(receita: Receita){

        viewModelScope.launch {
            repository.insert(receita)
            atualizarLista()

        }

    }
    fun atualizarReceita(receita: Receita){

        viewModelScope.launch {
            repository.update(receita)
            atualizarLista()

        }

    }

    fun deletarTodasReceitas(id:MutableList<Long>){

        viewModelScope.launch {

            repository.deleteAll(id)

        }


    }


    fun removerReceita(position:Int){

        viewModelScope.launch {
            val linha = listAll.value?.get(position)

            if (linha != null) {
                repository.delete(linha.id)
            }

        }

    }

}