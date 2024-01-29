package dev.subfly.kmpktorcoinbase.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TeamDto(
    val id: String? = null,
    val name: String? = null,
    val position: String? = null,
)
