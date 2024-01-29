package dev.subfly.kmpandroidcomposektor.util.extension

import dev.subfly.kmpandroidcomposektor.R
import dev.subfly.kmpktorcoinbase.core.util.CoinType

fun CoinType.getIcon(): Int {
    return when (this) {
        CoinType.COIN -> R.drawable.ic_coin
        CoinType.TOKEN -> R.drawable.ic_token
        CoinType.INVALID -> R.drawable.ic_iinvalid
    }
}
