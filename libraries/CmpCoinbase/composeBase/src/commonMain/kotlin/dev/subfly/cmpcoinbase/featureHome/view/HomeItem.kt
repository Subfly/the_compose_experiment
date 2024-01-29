package dev.subfly.cmpcoinbase.featureHome.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.subfly.cmpcoinbase.res.coinTypeIcon
import dev.subfly.cmpcoinbase.res.invalidTypeIcon
import dev.subfly.cmpcoinbase.res.tokenTypeIcon
import dev.subfly.cmpcoinbase.core.util.Platform
import dev.subfly.cmpcoinbase.core.util.PlatformManager
import dev.subfly.cmpcoinbase.core.model.CoinHomeModel
import dev.subfly.cmpcoinbase.core.util.CoinType
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeItem(
    model: CoinHomeModel,
    onClickItem: (CoinHomeModel) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickItem(model)
            }
            .padding(horizontal = 12.dp)
            .padding(bottom = 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            when (PlatformManager.currentPlatform) {
                Platform.ANDROID -> {
                    Image(
                        painter = painterResource(
                            when (model.type) {
                                CoinType.COIN -> "ic_coin.xml"
                                CoinType.TOKEN -> "ic_token.xml"
                                CoinType.INVALID -> "ic_iinvalid.xml"
                            }
                        ),
                        contentDescription = model.type.value,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier.size(48.dp)
                    )
                }
                Platform.IOS -> {
                    Icon(
                        imageVector = when (model.type) {
                            CoinType.COIN -> coinTypeIcon
                            CoinType.TOKEN -> tokenTypeIcon
                            CoinType.INVALID -> invalidTypeIcon
                        },
                        contentDescription = model.type.value,
                        tint = Color.Black,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = model.symbol,
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = model.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = if (model.isActive) Color.Green else Color.Gray,
                    shape = CircleShape,
                ),
        )
    }
}
