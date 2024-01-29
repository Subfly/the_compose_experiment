package dev.subfly.baseandroidcomposektor.featureDetail.domain.dao

import dev.subfly.baseandroidcomposektor.core.data.dto.CoinDto

interface CoinPaprikaDetailDao {
    suspend fun getCoinDetail(id: String): CoinDto
}
