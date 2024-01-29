package dev.subfly.cmpcoinbase.featureDetail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.subfly.cmpcoinbase.res.backArrow
import dev.subfly.cmpcoinbase.core.util.Platform
import dev.subfly.cmpcoinbase.core.util.PlatformManager
import dev.subfly.cmpcoinbase.core.util.Constants
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CoinDetailAppBar(
    coinSymbol: String?,
    coinActivity: Boolean,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        backgroundColor = Color.Black,
        contentColor = Color.White,
        title = {
            Text(text = coinSymbol ?: Constants.EMPTY_STRING)
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed
            ) {
                when (PlatformManager.currentPlatform) {
                    Platform.ANDROID -> {
                        Icon(
                            painter = painterResource("ic_arrow_back.xml"),
                            contentDescription = "Return to Home",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Platform.IOS -> {
                        Icon(
                            imageVector = backArrow,
                            contentDescription = "Return to Home",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        },
        actions = {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = if (coinActivity)
                            Color.Green
                        else
                            Color.Gray,
                        shape = CircleShape,
                    ),
            )
            Spacer(modifier = Modifier.padding(end = 12.dp))
        }
    )
}
