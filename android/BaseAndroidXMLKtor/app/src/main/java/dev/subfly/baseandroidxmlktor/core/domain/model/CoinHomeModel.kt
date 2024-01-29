package dev.subfly.baseandroidxmlktor.core.domain.model

import dev.subfly.baseandroidxmlktor.core.util.CoinType
import dev.subfly.baseandroidxmlktor.core.util.Constants

data class CoinHomeModel(
    val id: String,
    val name: String = Constants.EMPTY_STRING,
    val symbol: String = Constants.EMPTY_STRING,
    val isActive: Boolean = false,
    val isNew: Boolean = false,
    val type: CoinType = CoinType.COIN,
)
