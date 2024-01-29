package dev.subfly.baseandroidcomposektor.featureHome.view

import androidx.lifecycle.ViewModel
import dev.subfly.baseandroidcomposektor.core.util.Constants
import dev.subfly.baseandroidcomposektor.core.util.Resource
import dev.subfly.baseandroidcomposektor.featureHome.domain.usecase.GetAllCoinsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val getAllCoinsUseCase: GetAllCoinsUseCase) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

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

    override fun onCleared() {
        getCoinsJob?.cancel()
        super.onCleared()
    }
}
