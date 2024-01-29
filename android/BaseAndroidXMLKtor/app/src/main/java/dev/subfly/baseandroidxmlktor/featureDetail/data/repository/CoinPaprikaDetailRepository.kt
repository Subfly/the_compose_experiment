package dev.subfly.baseandroidxmlktor.featureDetail.data.repository

import dev.subfly.baseandroidxmlktor.core.data.dto.CoinDto
import dev.subfly.baseandroidxmlktor.featureDetail.domain.dao.CoinPaprikaDetailDao
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val COIN_ID_PATH = "/coins"

class CoinPaprikaDetailRepository(
    private val client: HttpClient,
) : CoinPaprikaDetailDao {
    override suspend fun getCoinDetail(id: String): CoinDto {
        return client
            .get("$COIN_ID_PATH/$id")
            .body<CoinDto>()
    }
}
