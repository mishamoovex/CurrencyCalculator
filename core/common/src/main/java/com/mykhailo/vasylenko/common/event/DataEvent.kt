package com.mykhailo.vasylenko.common.event

data class DataEvent<out T>(
    private val value: T
) : java.io.Serializable {

    private var isConsumed = false

    fun consume(todo: (T) -> Unit) {
        if (!isConsumed) {
            todo(value)
            isConsumed = true
        }
    }
}