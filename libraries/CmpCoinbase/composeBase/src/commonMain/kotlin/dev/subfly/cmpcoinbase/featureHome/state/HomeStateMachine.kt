package dev.subfly.cmpcoinbase.featureHome.state

import dev.subfly.cmpcoinbase.core.network.CoinPaprikaNetworking
import dev.subfly.cmpcoinbase.core.util.Constants
import dev.subfly.cmpcoinbase.core.util.Resource
import dev.subfly.cmpcoinbase.core.util.toCommonStateFlow
import dev.subfly.cmpcoinbase.featureHome.data.repository.CoinPaprikaHomeRepository
import dev.subfly.cmpcoinbase.featureHome.domain.usecase.GetAllCoinsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeStateMachine {
    private val getAllCoinsUseCase: GetAllCoinsUseCase = GetAllCoinsUseCase(
        repository = CoinPaprikaHomeRepository(
            client = CoinPaprikaNetworking.client
        )
    )

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state = _state.toCommonStateFlow()

    private var getCoinsJob: Job? = null

    init {
        getCoinsJob?.cancel()
        getCoinsJob = CoroutineScope(Dispatchers.IO).launch {
            getAllCoinsUseCase.fetchData().collectLatest { resource ->
                _state.update {
                    it.copy(
                        error = if (resource is Resource.Error)
                            resource.message
                        else
                            Constants.EMPTY_STRING,
                        isLoading = resource is Resource.Loading,
                        coins = if (resource is Resource.Success)
                            resource.data
                        else
                            it.coins
                    )
                }
            }
        }
    }

    fun reset() {
        getCoinsJob?.cancel()
    }
}
