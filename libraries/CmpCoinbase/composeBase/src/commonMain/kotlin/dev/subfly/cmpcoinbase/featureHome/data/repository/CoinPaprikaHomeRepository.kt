package dev.subfly.cmpcoinbase.featureHome.data.repository

import dev.subfly.cmpcoinbase.core.data.dto.CoinDto
import dev.subfly.cmpcoinbase.featureHome.domain.dao.CoinPaprikaHomeDao
import dev.subfly.cmpcoinbase.core.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val ALL_COINS_PATH = "/coins"

class CoinPaprikaHomeRepository(
    private val client: HttpClient
) : CoinPaprikaHomeDao {
    override suspend fun getAllCoins(): List<CoinDto> {
        return client
            .get("${Constants.COIN_PAPRIKA_BASE_URL}$ALL_COINS_PATH")
            .body<List<CoinDto>>()
    }
}
