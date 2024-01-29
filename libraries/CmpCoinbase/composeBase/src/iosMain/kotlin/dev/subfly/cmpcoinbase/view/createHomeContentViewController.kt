package dev.subfly.cmpcoinbase.view

import androidx.compose.ui.window.ComposeUIViewController
import dev.subfly.cmpcoinbase.featureHome.view.HomeContent
import dev.subfly.cmpcoinbase.core.model.CoinHomeModel
import platform.UIKit.UIViewController

fun createHomeContentViewController(
    coins: List<CoinHomeModel>,
    onClickItem: (CoinHomeModel) -> Unit,
): UIViewController = ComposeUIViewController {
    HomeContent(
        coins = coins,
        onClickItem = onClickItem
    )
}
