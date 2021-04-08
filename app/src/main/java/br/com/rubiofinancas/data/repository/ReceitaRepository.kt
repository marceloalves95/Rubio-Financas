package br.com.rubiofinancas.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.rubiofinancas.data.dao.ReceitaDao
import br.com.rubiofinancas.data.db.AppDatabase
import br.com.rubiofinancas.data.domain.Receita
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Rubio Finanças
 * @author Marcelo Alves
 * 15/03/2021
 */
class ReceitaRepository(application: Application) {

    private val receitaDao:ReceitaDao

    init {
        val database = AppDatabase.getDatabase(application)
        receitaDao = database.receitaDao()
    }

    suspend fun getAll():MutableList<Receita>{

        return receitaDao.getAll()
    }

    suspend fun insert(receita: Receita){

        receitaDao.insert(receita)

    }
    suspend fun update(receita: Receita){

        receitaDao.update(receita)

    }
    suspend fun delete(id:Long){

        receitaDao.delete(id)

    }

    suspend fun getMaxValor():Double{

        return receitaDao.getMaxValor()

    }

    suspend fun getMinValor():Double{

        return receitaDao.getMinValor()

    }

    suspend fun deleteAll(id: MutableList<Long>){

        receitaDao.deleteAll(id)

        Log.d("Id3", id.toString())

    }

    suspend fun getMedia():Double{

        return receitaDao.getMediaValor()

    }

}