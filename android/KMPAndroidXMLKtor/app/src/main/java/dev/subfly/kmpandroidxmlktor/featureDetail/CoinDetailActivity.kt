package dev.subfly.kmpandroidxmlktor.featureDetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dev.subfly.kmpandroidxmlktor.R
import dev.subfly.kmpandroidxmlktor.databinding.ActivityCoinDetailBinding
import dev.subfly.kmpandroidxmlktor.utils.extensions.getIcon
import dev.subfly.kmpandroidxmlktor.utils.extensions.setupInsets
import dev.subfly.kmpandroidxmlktor.utils.extensions.setupStatusBar
import dev.subfly.kmpktorcoinbase.core.extension.orFalse
import dev.subfly.kmpktorcoinbase.core.util.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CoinDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinDetailBinding
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

        binding = ActivityCoinDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupInsets(binding.root)
        setupStatusBar()
        setupToolbar()
        setupViewModel()
        setupListeners()
    }

    private fun setupToolbar() {
        with(binding) {
            detailBackButton.setOnClickListener {
                finish()
            }
            detailCoinSymbol.text = coinSymbol
            detailActivityIndicator.setImageResource(
                if (coinActivity)
                    R.drawable.green_circle
                else
                    R.drawable.gray_circle
            )
        }
    }

    private fun setupViewModel() {
        if (this::viewModel.isInitialized.not()) {
            viewModel = CoinDetailViewModel()
            viewModel.getCoinById(coinId)
        }
    }

    private fun setupListeners() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { uiState ->
                with(binding) {
                    when {
                        uiState.isLoading -> {
                            detailProgress.visibility = View.VISIBLE
                            detailContent.visibility = View.GONE
                            detailError.visibility = View.GONE
                        }
                        uiState.error.isNotEmpty() -> {
                            detailProgress.visibility = View.GONE
                            detailContent.visibility = View.GONE
                            detailError.visibility = View.VISIBLE
                            detailError.text = uiState.error
                        }
                        uiState.coin != null -> {
                            detailProgress.visibility = View.GONE
                            detailError.visibility = View.GONE
                            detailContent.visibility = View.VISIBLE
                            uiState.coin?.let { model ->
                                detailCoinType.setImageResource(model.type.getIcon())
                                detailCoinName.text = model.name
                                detailCoinDescription.text = model.coinDescription
                                detailCoinMessage.text = model.message
                            }
                        }
                    }
                }
            }
        }
    }
}
