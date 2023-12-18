package com.example.sigmaindustry.presentation.cart

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sigmaindustry.data.remote.dto.EntriesResponse
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.presentation.Dimens.ServiceCardSize


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
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {

                    Text(
                        "Service: ${entry.service.name}",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        fontSize = 20.sp
                    )

                val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/@${entry.service.geolocation.latitude},${entry.service.geolocation.longitude},18z")) }
                Button(onClick = { context.startActivity(intent) }) {
                    Text(text = "Open in GoogleMaps")
                }
            }
        }
        Text("Sent to: ${entry.email}", style = TextStyle(fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))
        Row(verticalAlignment = Alignment.CenterVertically) {Text("Message: ", style = TextStyle(fontWeight = FontWeight.Bold)); Text(entry.message.split("|")[0])}
    }
}