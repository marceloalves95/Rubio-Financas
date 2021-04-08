package br.com.rubiofinancas.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.rubiofinancas.data.domain.Despesa

/**
 * Rubio Finanças
 * @author Marcelo Alves
 * 13/03/2021
 */
@Dao
interface DespesaDao {

    @Query("SELECT * FROM despesa")
    fun getAll():LiveData<List<Despesa>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDespesas(vararg despesas: Despesa)
}