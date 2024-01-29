package dev.subfly.baseandroidcomposektor.core.domain.model

import dev.subfly.baseandroidcomposektor.core.util.CoinType
import dev.subfly.baseandroidcomposektor.core.util.Constants

data class CoinHomeModel(
    val id: String,
    val name: String = Constants.EMPTY_STRING,
    val symbol: String = Constants.EMPTY_STRING,
    val isActive: Boolean = false,
    val isNew: Boolean = false,
    val type: CoinType = CoinType.COIN,
)
