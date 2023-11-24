package com.example.sigmaindustry.presentation.cart

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.sigmaindustry.data.remote.dto.EntriesResponse
import com.example.sigmaindustry.presentation.Dimens
import kotlinx.coroutines.delay


@Composable
fun CartView(
    event: (CartViewEvent) -> Unit,
    viewModel: CartViewModel,
    state: State<CartState>
) {
    LaunchedEffect(state) {
        delay(2000)
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
            }
        }
    }
}


@Composable
fun CartCardView(entry: EntriesResponse) {
    Column {
        Text("Send to ${entry.email} message: ${entry.message}")
        Text("Service (id): ${entry.serviceId}")

        val context = LocalContext.current
        val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/%D0%94%D0%B8%D0%BD%D0%B0%D0%BC%D0%BE/@50.0209193,36.2283769,15z/data=!4m9!3m8!1s0x4127a1c538f602ed:0xd66871083e9f3c73!5m2!4m1!1i2!8m2!3d50.0192764!4d36.2383584!16s%2Fg%2F11xc0pdkc?entry=ttu")) }

        Button(onClick = { context.startActivity(intent) }) {
            Text(text = "Navigate to Google!")
        }

    }
}