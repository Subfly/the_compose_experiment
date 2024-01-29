package dev.subfly.cmpcoinbase.featureDetail.domain.dao

import dev.subfly.cmpcoinbase.core.data.dto.CoinDto

interface CoinPaprikaDetailDao {
    suspend fun getCoinDetail(id: String): CoinDto
}
