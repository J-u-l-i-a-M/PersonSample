package com.example.persons.util

import android.content.Context
import com.example.persons.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val dateTimePattern = "dd.MM.yyyy г."
    private const val dateTimePatternRequest = "yyyy-MM-dd"
    private const val dateTimePatternRequestBack = "dd-MM-yyyy"
    private const val emptyValue = "««"

    fun convertDate(dateText: String): String {
        if (dateText == "-" || dateText.isEmpty()) return emptyValue
        val date: Date
        try {
            // дата приходит в разных форматах (прямой и обратный)
            date = SimpleDateFormat(
                if (dateText.getOrNull(2) == '-')
                    dateTimePatternRequestBack else dateTimePatternRequest
            ).parse(dateText)
        } catch (e: ParseException) {
            e.printStackTrace()
            return emptyValue
        }
        return SimpleDateFormat(dateTimePattern, Locale.US).format(date)
    }

    fun getAgeString(context: Context, birthDay: String) : String {
        val age = getAge(birthDay)
        return String.format(Locale.US, context.getString(R.string.age),
            if (birthDay == emptyValue)
                birthDay
            else
                context.resources.getQuantityString(R.plurals.plurals_age, age, age)
        )
    }

    private fun getAge(birthDay: String): Int {
        val date: Date?
        try {
            date = SimpleDateFormat(dateTimePattern).parse(birthDay)
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0
        }

        if (date == null) return 0

        val birth = Calendar.getInstance()
        val today = Calendar.getInstance()

        birth.time = date

        val year = birth[Calendar.YEAR]
        val month = birth[Calendar.MONTH]
        val day = birth[Calendar.DAY_OF_MONTH]

        birth[year, month + 1] = day

        var age = today[Calendar.YEAR] - birth[Calendar.YEAR]

        if (today[Calendar.DAY_OF_YEAR] < birth[Calendar.DAY_OF_YEAR]) {
            age--
        }
        return age
    }
}
