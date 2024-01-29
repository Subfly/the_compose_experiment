package dev.subfly.cmpcoinbase.featureHome.domain.usecase

import dev.subfly.cmpcoinbase.core.data.mapper.toCoinHomeModel
import dev.subfly.cmpcoinbase.core.exception.InvalidIdException
import dev.subfly.cmpcoinbase.core.model.CoinHomeModel
import dev.subfly.cmpcoinbase.core.util.Resource
import dev.subfly.cmpcoinbase.featureHome.domain.dao.CoinPaprikaHomeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllCoinsUseCase(private val repository: CoinPaprikaHomeDao) {
    suspend fun fetchData(): Flow<Resource<List<CoinHomeModel>>> = flow {
        emit(Resource.Loading)
        try {
            val coinsFetched = repository
                .getAllCoins()
                .map {
                    it.toCoinHomeModel()
                }
            emit(Resource.Success(coinsFetched))
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
