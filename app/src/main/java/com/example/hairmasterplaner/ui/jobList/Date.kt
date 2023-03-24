package com.example.hairmasterplaner.ui.jobList

data class Date(
    val year: Int,
    val month: Int,
    val dayOfMonth: Int,
) {
    val dateInMils =
        year*365 + month*30 + dayOfMonth

    fun getFormattedDate(): String {
        val monthAsString = when(month){
            0 -> "января"
            1 -> "февраля"
            2 -> "марта"
            3 -> "апреля"
            4 -> "мая"
            5 -> "июня"
            6 -> "июля"
            7 -> "августа"
            8 -> "сентября"
            9 -> "октября"
            10 -> "ноября"
            else -> "декабря"
        }
        val answer = StringBuilder()
        answer.append(dayOfMonth)
        answer.append(" ")
        answer.append(monthAsString)
        answer.append(" ")
        answer.append(year)
        answer.append("г.")
        return answer.toString()
    }
}