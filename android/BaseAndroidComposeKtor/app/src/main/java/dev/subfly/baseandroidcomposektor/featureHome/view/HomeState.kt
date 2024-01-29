package dev.subfly.baseandroidcomposektor.featureHome.view

import dev.subfly.baseandroidcomposektor.core.domain.model.CoinHomeModel
import dev.subfly.baseandroidcomposektor.core.util.Constants

data class HomeState(
    val coins: List<CoinHomeModel> = emptyList(),
    val isLoading: Boolean = true,
    val error: String = Constants.EMPTY_STRING,
)
