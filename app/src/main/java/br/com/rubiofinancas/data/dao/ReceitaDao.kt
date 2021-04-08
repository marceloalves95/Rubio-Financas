package br.com.rubiofinancas.data.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import br.com.rubiofinancas.data.domain.Receita

/**
 * Rubio Finanças
 * @author Marcelo Alves
 * 13/03/2021
 */
@Dao
interface ReceitaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(receitas:Receita)
    @Update
    suspend fun update(receitas:Receita)
    @Query("DELETE FROM receita WHERE id = :id")
    suspend fun delete(id:Long)
    @Query("DELETE FROM receita WHERE id IN (:id)")
    suspend fun deleteAll(id:MutableList<Long>)
    @Query("SELECT * FROM receita")
    suspend fun getAll(): MutableList<Receita>
    @Query("SELECT MAX(valor) FROM receita")
    suspend fun getMaxValor():Double
    @Query("SELECT MIN(valor) FROM receita")
    suspend fun getMinValor():Double
    @Query("SELECT AVG(valor) FROM receita")
    suspend fun getMediaValor():Double


}