package com.example.sigmaindustry.presentation.cart

import android.content.Intent
import android.net.Uri
import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sigmaindustry.data.remote.dto.EntriesResponse
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.presentation.Dimens.ServiceCardSize
import com.example.sigmaindustry.presentation.Dimens.ServiceImageHeight
import kotlinx.coroutines.delay


@Composable
fun CartView(
    event: (CartViewEvent) -> Unit,
    viewModel: CartViewModel,
    state: State<CartState>
) {
    LaunchedEffect(null) {
        event(CartViewEvent.Load)
    }
    Column(
        modifier = Modifier
            .padding(
                top = Dimens.MediumPadding1,
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1
            )
            .statusBarsPadding()){
        LazyColumn() {
            items(state.value.history.entries.size) {
                CartCardView(entry = state.value.history.entries[it])
                Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
                Divider()
                Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
            }
        }
    }
}


@Composable
fun CartCardView(entry: EntriesResponse) {
    val context = LocalContext.current
    Column {
        Row {
            AsyncImage(
                modifier = Modifier
                    .size(ServiceCardSize)
                    .clip(MaterialTheme.shapes.medium),
                model = ImageRequest.Builder(context).data(entry.service.pictures[0]).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))
            Column {
                Text("Send to ${entry.email}")
                val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/@${entry.service.geolocation.latitude},${entry.service.geolocation.longitude},18z")) }
                Button(onClick = { context.startActivity(intent) }) {
                    Text(text = "Navigate to Google!")
                }
            }
        }
        Text("Message: ${entry.message.split("|")[0]}")
    }
}