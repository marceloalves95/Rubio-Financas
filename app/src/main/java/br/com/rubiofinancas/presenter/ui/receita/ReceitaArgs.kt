package br.com.rubiofinancas.presenter.ui.receita

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReceitaArgs(
        val data_hoje: String,
        val item: String,
        val categoria: String,
        val valor: Double,
        val mes: String,
        val ano: Int
) : Parcelable