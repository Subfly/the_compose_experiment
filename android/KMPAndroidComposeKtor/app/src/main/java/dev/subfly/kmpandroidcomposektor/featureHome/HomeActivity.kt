package dev.subfly.kmpandroidcomposektor.featureHome

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.subfly.kmpandroidcomposektor.featureDetail.CoinDetailActivity
import dev.subfly.kmpandroidcomposektor.featureHome.components.HomeAppBar
import dev.subfly.kmpandroidcomposektor.featureHome.components.HomeContent
import dev.subfly.kmpandroidcomposektor.util.extension.setupStatusBar
import dev.subfly.kmpktorcoinbase.core.util.Constants

class HomeActivity : ComponentActivity() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
        setupStatusBar()

        setContent {
            val uiState by viewModel.state.collectAsState()

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
                                onClickItem = {
                                    startActivity(
                                        Intent(
                                            this@HomeActivity,
                                            CoinDetailActivity::class.java,
                                        ).apply {
                                            putExtra(
                                                Constants.COIN_ID_KEY,
                                                it.id,
                                            )
                                            putExtra(
                                                Constants.COIN_SYMBOL_KEY,
                                                it.symbol,
                                            )
                                            putExtra(
                                                Constants.COIN_ACTIVITY_KEY,
                                                it.isActive,
                                            )
                                        }
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        if (this::viewModel.isInitialized.not()) {
            viewModel = HomeViewModel()
        }
    }
}
