package dev.subfly.kmpktorcoinbase.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class WhitePaperDto(
    val link: String? = null,
    val thumbnail: String? = null,
)
