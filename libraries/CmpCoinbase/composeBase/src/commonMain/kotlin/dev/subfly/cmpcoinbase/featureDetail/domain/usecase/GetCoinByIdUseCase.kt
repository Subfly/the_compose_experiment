package dev.subfly.cmpcoinbase.featureDetail.domain.usecase

import dev.subfly.cmpcoinbase.core.data.mapper.toCoinDetailModel
import dev.subfly.cmpcoinbase.core.exception.InvalidIdException
import dev.subfly.cmpcoinbase.core.model.CoinDetailModel
import dev.subfly.cmpcoinbase.core.util.Resource
import dev.subfly.cmpcoinbase.featureDetail.data.repository.CoinPaprikaDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCoinByIdUseCase(private val repository: CoinPaprikaDetailRepository) {
    suspend fun fetchData(coinId: String?): Flow<Resource<CoinDetailModel>> = flow {
        emit(Resource.Loading)
        try {
            if (coinId == null) {
                emit(Resource.Error("Coin can not be found, please try again..."))
            } else {
                val detailFetched = repository
                    .getCoinDetail(id = coinId)
                    .toCoinDetailModel()
                emit(Resource.Success(detailFetched))
            }
        } catch (e: InvalidIdException) {
            emit(Resource.Error(e.message))
        } catch (e: Exception) {
            // General exception handling for Ktor, no deep dive, sorry
            e.message?.let { errorMessage ->
                emit(Resource.Error(errorMessage))
            }
        }
    }
}
