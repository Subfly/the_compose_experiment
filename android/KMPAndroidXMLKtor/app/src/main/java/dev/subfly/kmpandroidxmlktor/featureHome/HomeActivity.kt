package dev.subfly.kmpandroidxmlktor.featureHome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.subfly.kmpandroidxmlktor.databinding.ActivityHomeBinding
import dev.subfly.kmpandroidxmlktor.featureDetail.CoinDetailActivity
import dev.subfly.kmpandroidxmlktor.utils.extensions.setupInsets
import dev.subfly.kmpandroidxmlktor.utils.extensions.setupStatusBar
import dev.subfly.kmpktorcoinbase.core.util.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupInsets(binding.root)
        setupStatusBar()
        setupViewModel()
        setupListeners()
    }

    private fun setupViewModel() {
        if (this::viewModel.isInitialized.not()) {
            viewModel = HomeViewModel()
        }
    }

    private fun setupListeners() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { uiState ->
                with(binding) {
                    when {
                        uiState.isLoading -> {
                            homeProgress.visibility = View.VISIBLE
                            homeError.visibility = View.GONE
                            homeContent.visibility = View.VISIBLE
                        }
                        uiState.error.isNotEmpty() -> {
                            homeProgress.visibility = View.GONE
                            homeContent.visibility = View.GONE
                            homeError.visibility = View.VISIBLE
                            homeError.text = uiState.error
                        }
                        uiState.coins.isEmpty().not() -> {
                            homeProgress.visibility = View.GONE
                            homeError.visibility = View.GONE
                            homeContent.visibility = View.VISIBLE
                            homeContent.layoutManager = LinearLayoutManager(
                                this@HomeActivity,
                            )
                            homeContent.adapter = HomeContentAdapter(
                                data = uiState.coins,
                                onClickItem = { coinId, coinSymbol, coinActivity ->
                                    startActivity(
                                        Intent(
                                            this@HomeActivity,
                                            CoinDetailActivity::class.java,
                                        ).apply {
                                            putExtra(
                                                Constants.COIN_ID_KEY,
                                                coinId,
                                            )
                                            putExtra(
                                                Constants.COIN_SYMBOL_KEY,
                                                coinSymbol,
                                            )
                                            putExtra(
                                                Constants.COIN_ACTIVITY_KEY,
                                                coinActivity,
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
}
