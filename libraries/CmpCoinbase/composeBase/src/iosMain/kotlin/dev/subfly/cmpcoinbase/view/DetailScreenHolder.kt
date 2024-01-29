package dev.subfly.cmpcoinbase.view

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.ComposeUIViewController
import dev.subfly.cmpcoinbase.featureDetail.state.CoinDetailStateMachine
import dev.subfly.cmpcoinbase.featureDetail.view.CoinDetailScreen
import kotlinx.coroutines.Dispatchers
import platform.UIKit.UIViewController

class DetailScreenHolder(
    coinId: String,
    coinSymbol: String,
    coinActivity: Boolean,
    onBackPressed: () -> Unit,
) {
    private val stateMachine: CoinDetailStateMachine = CoinDetailStateMachine()

    val viewController: UIViewController = ComposeUIViewController {
        val uiState by stateMachine.state.collectAsState(context = Dispatchers.Main)

        LaunchedEffect(true) {
            stateMachine.getCoinById(coinId)
        }

        CoinDetailScreen(
            coinSymbol = coinSymbol,
            coinActivity = coinActivity,
            uiState = uiState,
            onBackPressed = onBackPressed
        )
    }

    fun release() {
        stateMachine.reset()
    }
}
