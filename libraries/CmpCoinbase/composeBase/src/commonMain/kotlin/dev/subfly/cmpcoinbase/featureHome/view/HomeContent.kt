package dev.subfly.cmpcoinbase.featureHome.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import dev.subfly.cmpcoinbase.core.model.CoinHomeModel

@Composable
fun HomeContent(
    coins: List<CoinHomeModel>,
    onClickItem: (CoinHomeModel) -> Unit,
) {
    LazyColumn {
        items(coins) { model ->
            HomeItem(
                model = model,
                onClickItem = onClickItem
            )
        }
    }
}
