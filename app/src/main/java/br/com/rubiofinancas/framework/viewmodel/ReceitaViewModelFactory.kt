package br.com.rubiofinancas.framework.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Rubio Finanças
 * @author Marcelo Alves
 * 15/03/2021
 */
class ReceitaViewModelFactory (val application: Application): ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ReceitaViewModel(application) as T

}