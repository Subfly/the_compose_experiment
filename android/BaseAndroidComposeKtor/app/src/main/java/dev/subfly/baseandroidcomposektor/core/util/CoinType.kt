package dev.subfly.baseandroidcomposektor.core.util

import dev.subfly.baseandroidcomposektor.R

enum class CoinType(val value: String) {
    COIN("coin"),
    TOKEN("token"),
    INVALID("-"),
}

fun CoinType.getIcon(): Int {
    return when (this) {
        CoinType.COIN -> R.drawable.ic_coin
        CoinType.TOKEN -> R.drawable.ic_token
        CoinType.INVALID -> R.drawable.ic_iinvalid
    }
}
