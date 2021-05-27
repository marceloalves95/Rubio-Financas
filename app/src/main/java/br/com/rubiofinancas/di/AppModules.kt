package br.com.rubiofinancas.di

import androidx.room.Room
import br.com.rubiofinancas.data.db.AppDatabase
import br.com.rubiofinancas.data.repository.ReceitaRepository
import br.com.rubiofinancas.viewmodel.ReceitaViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by RubioAlves on 29/04/2021
 */

private const val NOME_BANCO_DE_DADOS = "financas.db"

val appModules = module {

    //Injeção do Banco de Dados <AppDatabase>
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).build()
    }
    //Injeção da interface ReceitaDao
    single{
        get<AppDatabase>().receitaDao()
    }
    //Injeção do Repository Receita
    single {
        ReceitaRepository(get())
    }
    //Injeção do ViewModel da Receita
    viewModel{
        ReceitaViewModel(get())
    }

}