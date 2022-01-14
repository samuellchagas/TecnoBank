package com.example.tecnobank.extension

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object HelperFunctions {

    fun converterToReal(value: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            .format(value)
    }

    private fun parseDateStringToDate(date:String):Date {
        return SimpleDateFormat(
            "dd/MM/yyyy",
            Locale("pt", "BR")
        ).parse(date)
    }

    fun getDateMonthFormat(date: String): String =
        SimpleDateFormat("dd MMMM", Locale("pt", "BR")).format(
            parseDateStringToDate(date)
        )

    fun formatDate(date: Date): String =
        SimpleDateFormat("dd/MM/yyyy").format(date)

}
