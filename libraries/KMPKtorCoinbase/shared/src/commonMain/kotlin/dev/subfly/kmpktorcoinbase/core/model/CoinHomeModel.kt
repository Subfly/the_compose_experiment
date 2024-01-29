package dev.subfly.kmpktorcoinbase.core.model

import dev.subfly.kmpktorcoinbase.core.util.CoinType
import dev.subfly.kmpktorcoinbase.core.util.Constants

data class CoinHomeModel(
    val id: String,
    val name: String = Constants.EMPTY_STRING,
    val symbol: String = Constants.EMPTY_STRING,
    val isActive: Boolean = false,
    val isNew: Boolean = false,
    val type: CoinType = CoinType.COIN,
)
