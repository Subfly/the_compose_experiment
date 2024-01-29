package dev.subfly.baseandroidcomposektor.featureDetail.view

import dev.subfly.baseandroidcomposektor.core.domain.model.CoinDetailModel
import dev.subfly.baseandroidcomposektor.core.util.Constants

data class CoinDetailState(
    val coin: CoinDetailModel? = null,
    val isLoading: Boolean = true,
    val error: String = Constants.EMPTY_STRING,
)
