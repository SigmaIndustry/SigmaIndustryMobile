package com.example.sigmaindustry.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sigmaindustry.R
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.presentation.Dimens.ExtraSmallPadding
import com.example.sigmaindustry.presentation.Dimens.ServiceCardSize

@Composable
fun ServiceCard(
    modifier: Modifier = Modifier,
    s: Service,
    onClick: ((Service) -> Unit)? = null
) {

    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke(s) },

        ) {
        AsyncImage(
            modifier = Modifier
                .size(ServiceCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(s.pictures[0]).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ServiceCardSize)
        ) {
            Text(
                text = s.name,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.black),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Price: ${if (s.price == 0.0) "Free" else s.price.toString() + " uah"}",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.black)
                    )
                    Text(
                        text = "Category: ${s.category}",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.black)
                    )
                }
            }
        }
    }
}

