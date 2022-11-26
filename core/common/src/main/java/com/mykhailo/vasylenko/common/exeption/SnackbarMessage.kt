package com.mykhailo.vasylenko.common.exeption

import androidx.annotation.StringRes
import java.util.*

data class SnackbarMessage(
    @StringRes val templateRes: Int,
    val placeholders: Array<String> = arrayOf(),
    val id: Long = UUID.randomUUID().mostSignificantBits
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SnackbarMessage

        if (templateRes != other.templateRes) return false
        if (!placeholders.contentEquals(other.placeholders)) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = templateRes
        result = 31 * result + placeholders.contentHashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}