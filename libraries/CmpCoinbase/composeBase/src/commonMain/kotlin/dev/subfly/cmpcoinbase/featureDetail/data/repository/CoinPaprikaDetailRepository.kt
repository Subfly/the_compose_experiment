package dev.subfly.cmpcoinbase.featureDetail.data.repository

import dev.subfly.cmpcoinbase.core.data.dto.CoinDto
import dev.subfly.cmpcoinbase.featureDetail.domain.dao.CoinPaprikaDetailDao
import dev.subfly.cmpcoinbase.core.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val COIN_ID_PATH = "/coins"

class CoinPaprikaDetailRepository(
    private val client: HttpClient,
) : CoinPaprikaDetailDao {
    override suspend fun getCoinDetail(id: String): CoinDto {
        return client
            .get("${Constants.COIN_PAPRIKA_BASE_URL}$COIN_ID_PATH/$id")
            .body<CoinDto>()
    }
}
