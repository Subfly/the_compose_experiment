package dev.subfly.baseandroidcomposektor.featureDetail.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.subfly.baseandroidcomposektor.core.domain.model.CoinDetailModel
import dev.subfly.baseandroidcomposektor.core.util.Constants
import dev.subfly.baseandroidcomposektor.core.util.getIcon

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
            Image(
                painter = painterResource(
                    id = model.type.getIcon(),
                ),
                contentDescription = Constants.EMPTY_STRING,
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 12.dp),
            )
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
                text = model.description,
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
