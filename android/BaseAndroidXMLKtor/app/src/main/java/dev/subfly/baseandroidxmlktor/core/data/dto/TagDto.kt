package dev.subfly.baseandroidxmlktor.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    val coin_counter: Int? = null,
    val ico_counter: Int? = null,
    val id: String? = null,
    val name: String? = null,
)
