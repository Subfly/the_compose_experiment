package dev.subfly.kmpktorcoinbase.featureHome.domain.dao

import dev.subfly.kmpktorcoinbase.core.data.dto.CoinDto

interface CoinPaprikaHomeDao {
    suspend fun getAllCoins(): List<CoinDto>
}
