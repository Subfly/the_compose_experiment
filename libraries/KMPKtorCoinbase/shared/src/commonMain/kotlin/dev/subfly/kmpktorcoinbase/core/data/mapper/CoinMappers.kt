package dev.subfly.kmpktorcoinbase.core.data.mapper

import dev.subfly.kmpktorcoinbase.core.exception.InvalidIdException
import dev.subfly.kmpktorcoinbase.core.extension.orFalse
import dev.subfly.kmpktorcoinbase.core.util.CoinType
import dev.subfly.kmpktorcoinbase.core.data.dto.CoinDto
import dev.subfly.kmpktorcoinbase.core.model.CoinDetailModel
import dev.subfly.kmpktorcoinbase.core.model.CoinHomeModel

fun CoinDto.toCoinHomeModel(): CoinHomeModel {
    id?.let {  notNullId ->
        return CoinHomeModel(
            id = notNullId,
            name = name.orEmpty(),
            symbol = symbol.orEmpty(),
            isActive = is_active.orFalse(),
            isNew = is_new.orFalse(),
            type = when(type) {
                CoinType.COIN.value -> CoinType.COIN
                CoinType.TOKEN.value -> CoinType.TOKEN
                else -> CoinType.INVALID
            }
        )
    }
    throw InvalidIdException()
}

fun CoinDto.toCoinDetailModel() : CoinDetailModel {
    id?.let {
        return CoinDetailModel(
            id = id,
            name = name.orEmpty(),
            symbol = symbol.orEmpty(),
            message = message.orEmpty(),
            coinDescription = description.orEmpty(),
            isActive = is_active.orFalse(),
            type = when(type) {
                CoinType.COIN.value -> CoinType.COIN
                CoinType.TOKEN.value -> CoinType.TOKEN
                else -> CoinType.INVALID
            }
        )
    }
    throw InvalidIdException()
}