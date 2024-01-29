package dev.subfly.baseandroidcomposektor.featureHome.domain.dao

import dev.subfly.baseandroidcomposektor.core.data.dto.CoinDto

interface CoinPaprikaHomeDao {
    suspend fun getAllCoins(): List<CoinDto>
}
