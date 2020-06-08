package com.example.persons.util

import java.util.*

object StringUtils {
    fun capitalizeFirstLetter(original: String): String {
        if (original.isEmpty())
            return original

        val firstCodePoint = Character.codePointAt(original, 0)
        val charCount = Character.charCount(firstCodePoint)

        val lang = Locale.getDefault().language
        val localeDependent = lang === "tr" || lang === "az" || lang === "lt"

        val firstChar = if (localeDependent)
            String(Character.toChars(firstCodePoint)).toUpperCase()
        else
            String(Character.toChars(Character.toTitleCase(firstCodePoint)))

        return firstChar + original.substring(charCount).toLowerCase()
    }
}