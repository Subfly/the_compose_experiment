package dev.subfly.baseandroidxmlktor.featureDetail.view

import dev.subfly.baseandroidxmlktor.core.domain.model.CoinDetailModel
import dev.subfly.baseandroidxmlktor.core.util.Constants

data class CoinDetailState(
    val coin: CoinDetailModel? = null,
    val isLoading: Boolean = true,
    val error: String = Constants.EMPTY_STRING,
)
