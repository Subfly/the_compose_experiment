package dev.subfly.kmpandroidcmpktor.featureDetail

import androidx.lifecycle.ViewModel
import dev.subfly.cmpcoinbase.featureDetail.state.CoinDetailState
import dev.subfly.cmpcoinbase.featureDetail.state.CoinDetailStateMachine
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
