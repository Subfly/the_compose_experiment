package dev.subfly.baseandroidxmlktor.featureDetail.domain.dao

import dev.subfly.baseandroidxmlktor.core.data.dto.CoinDto

interface CoinPaprikaDetailDao {
    suspend fun getCoinDetail(id: String): CoinDto
}
