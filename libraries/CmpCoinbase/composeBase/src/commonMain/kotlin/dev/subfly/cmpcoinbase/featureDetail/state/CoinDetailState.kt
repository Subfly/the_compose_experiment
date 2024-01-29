package dev.subfly.cmpcoinbase.featureDetail.state

import dev.subfly.cmpcoinbase.core.model.CoinDetailModel
import dev.subfly.cmpcoinbase.core.util.Constants

data class CoinDetailState(
    val coin: CoinDetailModel? = null,
    val isLoading: Boolean = true,
    val error: String = Constants.EMPTY_STRING,
)
