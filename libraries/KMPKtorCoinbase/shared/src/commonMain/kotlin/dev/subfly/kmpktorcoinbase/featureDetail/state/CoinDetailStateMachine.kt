package dev.subfly.kmpktorcoinbase.featureDetail.state

import dev.subfly.kmpktorcoinbase.core.network.CoinPaprikaNetworking
import dev.subfly.kmpktorcoinbase.featureDetail.domain.usecase.GetCoinByIdUseCase
import dev.subfly.kmpktorcoinbase.core.util.Constants
import dev.subfly.kmpktorcoinbase.core.util.Resource
import dev.subfly.kmpktorcoinbase.core.util.toCommonStateFlow
import dev.subfly.kmpktorcoinbase.featureDetail.data.repository.CoinPaprikaDetailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinDetailStateMachine {
    private val getCoinDetailUseCase: GetCoinByIdUseCase = GetCoinByIdUseCase(
        repository = CoinPaprikaDetailRepository(
            client = CoinPaprikaNetworking.client
        )
    )

    private val _state: MutableStateFlow<CoinDetailState> = MutableStateFlow(CoinDetailState())
    val state = _state.toCommonStateFlow()

    private var getCoinsJob: Job? = null

    fun getCoinById(id: String?) {
        getCoinsJob?.cancel()
        getCoinsJob = CoroutineScope(Dispatchers.IO).launch {
            getCoinDetailUseCase.fetchData(id).collectLatest { resource ->
                _state.update {
                    it.copy(
                        error = if (resource is Resource.Error)
                            resource.message
                        else
                            Constants.EMPTY_STRING,
                        isLoading = resource is Resource.Loading,
                        coin = if (resource is Resource.Success)
                            resource.data
                        else
                            it.coin
                    )
                }
            }
        }
    }

    fun reset() {
        getCoinsJob?.cancel()
    }
}
