package dev.subfly.cmpcoinbase.view

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import dev.subfly.cmpcoinbase.core.util.CoinType
import dev.subfly.cmpcoinbase.res.coinTypeIcon
import dev.subfly.cmpcoinbase.res.invalidTypeIcon
import dev.subfly.cmpcoinbase.res.tokenTypeIcon
import platform.UIKit.UIViewController

fun createCoinImageViewController(
    coinType: CoinType,
): UIViewController = ComposeUIViewController {
    Icon(
        imageVector = when (coinType) {
            CoinType.COIN -> coinTypeIcon
            CoinType.TOKEN -> tokenTypeIcon
            CoinType.INVALID -> invalidTypeIcon
        },
        contentDescription = coinType.value,
        tint = Color.Black,
        modifier = Modifier.size(300.dp)
    )
}
