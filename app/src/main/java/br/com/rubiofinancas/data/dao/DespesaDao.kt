package br.com.rubiofinancas.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.rubiofinancas.data.domain.Despesa
import br.com.rubiofinancas.data.domain.Receita

/**
 * Rubio Finanças
 * @author Marcelo Alves
 * 13/03/2021
 */
@Dao
interface DespesaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(despesa:Despesa)
    @Update
    suspend fun update(despesa: Despesa)
    @Query("DELETE FROM despesa WHERE id = :id")
    suspend fun delete(id:Long)
    @Query("DELETE FROM despesa WHERE id IN (:id)")
    suspend fun deleteAll(id:MutableList<Long>)
    @Query("SELECT * FROM despesa")
    suspend fun getAll(): MutableList<Despesa>

}