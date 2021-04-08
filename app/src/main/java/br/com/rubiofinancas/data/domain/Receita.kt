package br.com.rubiofinancas.data.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Rubio Finanças
 * @author Marcelo Alves
 * 13/03/2021
 */
@Entity(tableName = "receita")
data class Receita(

    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val data_hoje:String,
    val item:String,
    val categoria:String,
    val valor:Double,
    val mes:String,
    val ano:Int,
    var selected:Boolean = false
)
