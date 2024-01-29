package dev.subfly.kmpktorcoinbase.featureHome.state

import dev.subfly.kmpktorcoinbase.core.model.CoinHomeModel
import dev.subfly.kmpktorcoinbase.core.util.Constants

data class HomeState(
    val coins: List<CoinHomeModel> = emptyList(),
    val isLoading: Boolean = true,
    val error: String = Constants.EMPTY_STRING,
)
