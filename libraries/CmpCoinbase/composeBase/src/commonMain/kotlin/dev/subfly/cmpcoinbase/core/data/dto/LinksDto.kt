package dev.subfly.cmpcoinbase.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LinksDto(
    val explorer: List<String>? = null,
    val facebook: List<String>? = null,
    val medium: List<String>? = null,
    val reddit: List<String>? = null,
    val source_code: List<String>? = null,
    val website: List<String>? = null,
    val youtube: List<String>? = null,
)
