package br.com.rubiofinancas.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.rubiofinancas.data.dao.DespesaDao
import br.com.rubiofinancas.data.dao.ReceitaDao
import br.com.rubiofinancas.data.domain.Despesa
import br.com.rubiofinancas.data.domain.Receita

/**
 * Rubio Finanças
 * @author Marcelo Alves
 * 13/03/2021
 */
@Database(entities = [Receita::class, Despesa::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun receitaDao():ReceitaDao
    abstract fun despesaDao():DespesaDao

    companion object {

        private const val NOME_BANCO_DE_DADOS = "financas.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {

                return tempInstance

            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        NOME_BANCO_DE_DADOS
                ).fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                return instance

            }

        }




    }



}