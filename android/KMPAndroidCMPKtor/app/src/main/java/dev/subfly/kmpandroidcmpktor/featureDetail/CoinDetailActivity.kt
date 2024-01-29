package dev.subfly.kmpandroidcmpktor.featureDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.subfly.cmpcoinbase.core.extension.orFalse
import dev.subfly.cmpcoinbase.core.util.Constants
import dev.subfly.cmpcoinbase.featureDetail.view.CoinDetailScreen
import dev.subfly.kmpandroidcmpktor.util.extension.setupStatusBar

class CoinDetailActivity : ComponentActivity() {
    private lateinit var viewModel: CoinDetailViewModel

    private val coinId by lazy {
        intent.extras?.getString(Constants.COIN_ID_KEY)
    }
    private val coinSymbol by lazy {
        intent.extras?.getString(Constants.COIN_SYMBOL_KEY)
    }
    private val coinActivity by lazy {
        intent.extras?.getBoolean(Constants.COIN_ACTIVITY_KEY).orFalse()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
        setupStatusBar()

        setContent {
            val uiState by viewModel.state.collectAsState()

            CoinDetailScreen(
                coinSymbol = coinSymbol.orEmpty(),
                coinActivity = coinActivity,
                uiState = uiState,
                onBackPressed = ::finish
            )
        }
    }

    private fun setupViewModel() {
        if (this::viewModel.isInitialized.not()) {
            viewModel = CoinDetailViewModel()
            viewModel.getCoinById(coinId)
        }
    }
}
