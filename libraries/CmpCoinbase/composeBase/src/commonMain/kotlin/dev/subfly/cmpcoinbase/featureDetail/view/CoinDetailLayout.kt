package dev.subfly.cmpcoinbase.featureDetail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.subfly.cmpcoinbase.res.coinTypeIcon
import dev.subfly.cmpcoinbase.res.invalidTypeIcon
import dev.subfly.cmpcoinbase.res.tokenTypeIcon
import dev.subfly.cmpcoinbase.core.util.Platform
import dev.subfly.cmpcoinbase.core.util.PlatformManager
import dev.subfly.cmpcoinbase.core.model.CoinDetailModel
import dev.subfly.cmpcoinbase.core.util.CoinType
import dev.subfly.cmpcoinbase.core.util.Constants
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CoinDetailLayout(
    model: CoinDetailModel,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {
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
                        contentDescription = Constants.EMPTY_STRING,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .size(300.dp)
                            .padding(bottom = 12.dp),
                    )
                }
                Platform.IOS -> {
                    Icon(
                        imageVector = when (model.type) {
                            CoinType.COIN -> coinTypeIcon
                            CoinType.TOKEN -> tokenTypeIcon
                            CoinType.INVALID -> invalidTypeIcon
                        },
                        contentDescription = Constants.EMPTY_STRING,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(300.dp)
                            .padding(bottom = 12.dp),
                    )
                }
            }
            Text(
                text = model.name,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
            )
            Text(
                text = model.message,
                style = TextStyle(
                    fontSize = 18.sp,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
            )
            Text(
                text = model.coinDescription,
                style = TextStyle(
                    fontSize = 18.sp,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}
