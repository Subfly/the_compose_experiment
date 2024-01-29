package dev.subfly.cmpcoinbase.view

import androidx.compose.ui.window.ComposeUIViewController
import dev.subfly.cmpcoinbase.featureHome.view.HomeItem
import dev.subfly.cmpcoinbase.core.model.CoinHomeModel
import platform.UIKit.UIViewController

fun createHomeItemViewController(
    model: CoinHomeModel,
    onClickItem: (CoinHomeModel) -> Unit,
): UIViewController = ComposeUIViewController {
    HomeItem(
        model = model,
        onClickItem = onClickItem
    )
}
