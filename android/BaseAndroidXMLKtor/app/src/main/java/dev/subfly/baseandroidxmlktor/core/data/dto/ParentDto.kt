package dev.subfly.baseandroidxmlktor.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ParentDto(
    val id: String? = null,
    val name: String? = null,
    val symbol: String? = null,
)
