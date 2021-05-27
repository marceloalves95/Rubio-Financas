package br.com.rubiofinancas.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rubiofinancas.data.repository.ReceitaRepository

/**
 * Rubio Finanças
 * @author Marcelo Alves
 * 15/03/2021
 */
class ReceitaViewModelFactory (private val repository:ReceitaRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReceitaViewModel(repository) as T
    }


}