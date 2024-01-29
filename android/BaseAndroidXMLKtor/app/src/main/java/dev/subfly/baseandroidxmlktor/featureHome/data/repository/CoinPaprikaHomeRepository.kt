package dev.subfly.baseandroidxmlktor.featureHome.data.repository

import dev.subfly.baseandroidxmlktor.featureHome.domain.dao.CoinPaprikaHomeDao
import dev.subfly.baseandroidxmlktor.core.data.dto.CoinDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val ALL_COINS_PATH = "/coins"

class CoinPaprikaHomeRepository(
    private val client: HttpClient
) : CoinPaprikaHomeDao {
    override suspend fun getAllCoins(): List<CoinDto> {
        return client
            .get(ALL_COINS_PATH)
            .body<List<CoinDto>>()
    }
}
