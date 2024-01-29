package dev.subfly.baseandroidcomposektor.featureHome.view.components

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.subfly.baseandroidcomposektor.R
import dev.subfly.baseandroidcomposektor.core.domain.model.CoinHomeModel
import dev.subfly.baseandroidcomposektor.core.util.getIcon

@Composable
fun HomeContent(
    coins: List<CoinHomeModel>,
    onClickItem: (CoinHomeModel) -> Unit,
) {
    LazyColumn {
        items(coins) { model ->
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
                    Image(
                        painter = painterResource(
                            id = model.type.getIcon()
                        ),
                        contentDescription = stringResource(
                            id = R.string.coin_type
                        ),
                        modifier = Modifier.size(48.dp)
                    )
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
                            color = if (model.isActive)
                                Color.Green
                            else
                                Color.Gray,
                            shape = CircleShape,
                        ),
                )
            }
        }
    }
}
