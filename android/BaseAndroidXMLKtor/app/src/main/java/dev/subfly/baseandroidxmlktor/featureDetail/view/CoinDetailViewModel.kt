package dev.subfly.baseandroidxmlktor.featureDetail.view

import androidx.lifecycle.ViewModel
import dev.subfly.baseandroidxmlktor.core.util.Constants
import dev.subfly.baseandroidxmlktor.core.util.Resource
import dev.subfly.baseandroidxmlktor.featureDetail.domain.usecase.GetCoinByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinDetailViewModel(
    private val getCoinDetailUseCase: GetCoinByIdUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<CoinDetailState> = MutableStateFlow(CoinDetailState())
    val state: StateFlow<CoinDetailState> = _state.asStateFlow()

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

    override fun onCleared() {
        getCoinsJob?.cancel()
        super.onCleared()
    }
}
