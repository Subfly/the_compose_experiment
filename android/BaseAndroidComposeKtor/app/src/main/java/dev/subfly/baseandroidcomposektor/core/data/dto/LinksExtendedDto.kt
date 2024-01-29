package dev.subfly.baseandroidcomposektor.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LinksExtendedDto(
    val stats: StatsDto? = null,
    val type: String? = null,
    val url: String? = null,
)
