package dev.subfly.cmpcoinbase.featureDetail.view

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
import dev.subfly.cmpcoinbase.featureDetail.state.CoinDetailState

@Composable
fun CoinDetailScreen(
    coinSymbol: String,
    coinActivity: Boolean,
    uiState: CoinDetailState,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            CoinDetailAppBar(
                coinSymbol = coinSymbol,
                coinActivity = coinActivity,
                onBackPressed = onBackPressed,
            )
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
                uiState.coin != null -> {
                    CoinDetailLayout(model = uiState.coin)
                }
            }
        }
    }
}
