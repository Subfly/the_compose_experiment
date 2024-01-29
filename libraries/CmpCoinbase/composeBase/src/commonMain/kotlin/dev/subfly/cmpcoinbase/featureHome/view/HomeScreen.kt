package dev.subfly.cmpcoinbase.featureHome.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.subfly.cmpcoinbase.core.model.CoinHomeModel
import dev.subfly.cmpcoinbase.featureHome.state.HomeState

@Composable
fun HomeScreen(
    uiState: HomeState,
    onClickItem: (CoinHomeModel) -> Unit
) {
    Scaffold(
        topBar = {
            HomeAppBar()
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
            contentAlignment = Alignment.Center,
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        color = Color.Black
                    )
                }
                uiState.error.isNotEmpty() -> {
                    Text(
                        text = uiState.error
                    )
                }
                uiState.coins.isNotEmpty() -> {
                    HomeContent(
                        coins = uiState.coins,
                        onClickItem = onClickItem
                    )
                }
            }
        }
    }
}
