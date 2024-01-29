package dev.subfly.kmpandroidxmlktor.featureDetail

import androidx.lifecycle.ViewModel
import dev.subfly.kmpktorcoinbase.featureDetail.state.CoinDetailState
import dev.subfly.kmpktorcoinbase.featureDetail.state.CoinDetailStateMachine
import kotlinx.coroutines.flow.StateFlow

class CoinDetailViewModel : ViewModel() {
    private val _stateMachine: CoinDetailStateMachine = CoinDetailStateMachine()
    val state: StateFlow<CoinDetailState> = _stateMachine.state

    fun getCoinById(id: String?) {
        _stateMachine.getCoinById(id = id)
    }

    override fun onCleared() {
        _stateMachine.reset()
        super.onCleared()
    }
}
