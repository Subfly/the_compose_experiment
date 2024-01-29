package dev.subfly.cmpcoinbase.featureHome.state

import dev.subfly.cmpcoinbase.core.model.CoinHomeModel
import dev.subfly.cmpcoinbase.core.util.Constants

data class HomeState(
    val coins: List<CoinHomeModel> = emptyList(),
    val isLoading: Boolean = true,
    val error: String = Constants.EMPTY_STRING,
)
