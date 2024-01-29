package dev.subfly.cmpcoinbase.view

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.ComposeUIViewController
import dev.subfly.cmpcoinbase.core.model.CoinHomeModel
import dev.subfly.cmpcoinbase.featureHome.state.HomeStateMachine
import dev.subfly.cmpcoinbase.featureHome.view.HomeScreen
import kotlinx.coroutines.Dispatchers
import platform.UIKit.UIViewController

class HomeScreenHolder(
    onClickItem: (CoinHomeModel) -> Unit,
) {
    private val stateMachine: HomeStateMachine = HomeStateMachine()

    val viewController: UIViewController = ComposeUIViewController {
        val uiState by stateMachine.state.collectAsState(context = Dispatchers.Main)

        HomeScreen(
            uiState = uiState,
            onClickItem = onClickItem
        )
    }

    fun release() {
        stateMachine.reset()
    }
}
