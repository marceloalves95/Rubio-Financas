package br.com.rubiofinancas.data.repository

import android.app.Application
import br.com.rubiofinancas.data.dao.DespesaDao
import br.com.rubiofinancas.data.db.AppDatabase
import br.com.rubiofinancas.data.domain.Despesa

class DespesaRepository(application: Application) {

    private val despesaDao:DespesaDao

    init {
        val database = AppDatabase.getDatabase(application)
        despesaDao = database.despesaDao()
    }

    suspend fun getAll():MutableList<Despesa>{

        return despesaDao.getAll()
    }

    suspend fun insert(despesa: Despesa){

        despesaDao.insert(despesa)

    }
    suspend fun update(despesa: Despesa){

        despesaDao.update(despesa)

    }
    suspend fun delete(id:Long){

        despesaDao.delete(id)

    }

    suspend fun deleteAll(id: MutableList<Long>){

        despesaDao.deleteAll(id)

    }

}