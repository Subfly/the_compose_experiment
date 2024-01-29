package dev.subfly.cmpcoinbase.featureHome.domain.dao

import dev.subfly.cmpcoinbase.core.data.dto.CoinDto

interface CoinPaprikaHomeDao {
    suspend fun getAllCoins(): List<CoinDto>
}
