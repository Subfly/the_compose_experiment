package dev.subfly.baseandroidxmlktor.featureDetail.domain.usecase

import dev.subfly.baseandroidxmlktor.core.data.mapper.toCoinDetailModel
import dev.subfly.baseandroidxmlktor.core.domain.model.CoinDetailModel
import dev.subfly.baseandroidxmlktor.core.exception.InvalidIdException
import dev.subfly.baseandroidxmlktor.core.util.Resource
import dev.subfly.baseandroidxmlktor.featureDetail.data.repository.CoinPaprikaDetailRepository
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
