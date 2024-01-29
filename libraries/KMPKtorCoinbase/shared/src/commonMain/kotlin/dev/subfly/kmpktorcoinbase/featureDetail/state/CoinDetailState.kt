package dev.subfly.kmpktorcoinbase.featureDetail.state

import dev.subfly.kmpktorcoinbase.core.model.CoinDetailModel
import dev.subfly.kmpktorcoinbase.core.util.Constants

data class CoinDetailState(
    val coin: CoinDetailModel? = null,
    val isLoading: Boolean = true,
    val error: String = Constants.EMPTY_STRING,
)
