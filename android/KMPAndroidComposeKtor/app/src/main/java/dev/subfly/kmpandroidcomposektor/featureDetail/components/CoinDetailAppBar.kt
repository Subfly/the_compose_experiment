package dev.subfly.kmpandroidcomposektor.featureDetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.subfly.kmpandroidcomposektor.R
import dev.subfly.kmpktorcoinbase.core.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailAppBar(
    coinSymbol: String?,
    coinActivity: Boolean,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
        ),
        title = {
            Text(text = coinSymbol ?: Constants.EMPTY_STRING)
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(
                        id = R.string.return_to_home
                    )
                )
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
