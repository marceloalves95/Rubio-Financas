package br.com.rubiofinancas.data.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Rubio Finanças
 * @author Marcelo Alves
 * 13/03/2021
 */
@Entity(tableName = "despesa")
data class Despesa(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val item:String,
    val categoria:String,
    val valor:String,
    val tipo_pagamento:String,
    val mes:String,
    val ano:Int
)