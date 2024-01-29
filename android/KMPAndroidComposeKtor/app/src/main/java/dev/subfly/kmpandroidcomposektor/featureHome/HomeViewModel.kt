package dev.subfly.kmpandroidcomposektor.featureHome

import androidx.lifecycle.ViewModel
import dev.subfly.kmpktorcoinbase.featureHome.state.HomeState
import dev.subfly.kmpktorcoinbase.featureHome.state.HomeStateMachine
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _stateMachine: HomeStateMachine = HomeStateMachine()
    val state: StateFlow<HomeState> = _stateMachine.state

    override fun onCleared() {
       _stateMachine.reset()
        super.onCleared()
    }
}
