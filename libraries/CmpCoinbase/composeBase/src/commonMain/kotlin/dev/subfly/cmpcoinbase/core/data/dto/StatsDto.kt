package dev.subfly.cmpcoinbase.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class StatsDto(
    val contributors: Int? = null,
    val stars: Int? = null,
    val subscribers: Int? = null,
)
