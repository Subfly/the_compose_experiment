package dev.subfly.kmpandroidcmpktor.featureHome

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.subfly.cmpcoinbase.core.util.Constants
import dev.subfly.cmpcoinbase.featureHome.view.HomeScreen
import dev.subfly.kmpandroidcmpktor.featureDetail.CoinDetailActivity
import dev.subfly.kmpandroidcmpktor.util.extension.setupStatusBar

class HomeActivity : ComponentActivity() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
        setupStatusBar()

        setContent {
            val uiState by viewModel.state.collectAsState()

            HomeScreen(
                uiState = uiState,
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

    private fun setupViewModel() {
        if (this::viewModel.isInitialized.not()) {
            viewModel = HomeViewModel()
        }
    }
}
