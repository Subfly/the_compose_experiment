package dev.subfly.kmpktorcoinbase.featureDetail.domain.dao

import dev.subfly.kmpktorcoinbase.core.data.dto.CoinDto

interface CoinPaprikaDetailDao {
    suspend fun getCoinDetail(id: String): CoinDto
}
