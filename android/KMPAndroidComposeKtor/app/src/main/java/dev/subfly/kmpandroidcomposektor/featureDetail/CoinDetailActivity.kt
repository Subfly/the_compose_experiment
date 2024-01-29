package dev.subfly.kmpandroidcomposektor.featureDetail

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
import dev.subfly.kmpandroidcomposektor.featureDetail.components.CoinDetailAppBar
import dev.subfly.kmpandroidcomposektor.featureDetail.components.CoinDetailLayout
import dev.subfly.kmpandroidcomposektor.util.extension.setupStatusBar
import dev.subfly.kmpktorcoinbase.core.extension.orFalse
import dev.subfly.kmpktorcoinbase.core.util.Constants

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

            Scaffold(
                topBar = {
                    CoinDetailAppBar(
                        coinSymbol = coinSymbol,
                        coinActivity = coinActivity,
                        onBackPressed = ::finish
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
                            uiState.coin?.let { model ->
                                CoinDetailLayout(model = model)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        if (this::viewModel.isInitialized.not()) {
            viewModel = CoinDetailViewModel()
            viewModel.getCoinById(coinId)
        }
    }
}
