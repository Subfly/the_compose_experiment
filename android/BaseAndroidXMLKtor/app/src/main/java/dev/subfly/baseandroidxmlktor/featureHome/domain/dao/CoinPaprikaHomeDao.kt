package dev.subfly.baseandroidxmlktor.featureHome.domain.dao

import dev.subfly.baseandroidxmlktor.core.data.dto.CoinDto

interface CoinPaprikaHomeDao {
    suspend fun getAllCoins(): List<CoinDto>
}
