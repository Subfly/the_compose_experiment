package dev.subfly.baseandroidxmlktor.featureHome.view

import dev.subfly.baseandroidxmlktor.core.util.Constants
import dev.subfly.baseandroidxmlktor.core.domain.model.CoinHomeModel

data class HomeState(
    val coins: List<CoinHomeModel> = emptyList(),
    val isLoading: Boolean = true,
    val error: String = Constants.EMPTY_STRING,
)
