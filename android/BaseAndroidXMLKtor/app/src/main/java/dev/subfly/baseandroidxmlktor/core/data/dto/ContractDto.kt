package dev.subfly.baseandroidxmlktor.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ContractDto(
    val contract: String? = null,
    val platform: String? = null,
    val type: String? = null,
)
