package dev.subfly.baseandroidcomposektor.core.util

sealed class Resource<out T> {
    data class Error(
        val message: String
    ): Resource<Nothing>()

    data class Success<out T: Any>(
        val data: T
    ): Resource<T>()

    data object Loading: Resource<Nothing>()
}
