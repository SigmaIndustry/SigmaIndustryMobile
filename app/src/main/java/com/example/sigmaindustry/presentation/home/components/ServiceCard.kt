package com.example.sigmaindustry.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sigmaindustry.R
import com.example.sigmaindustry.data.remote.dto.SearchResult
import com.example.sigmaindustry.presentation.Dimens.ExtraSmallPadding
import com.example.sigmaindustry.presentation.Dimens.ExtraSmallPadding2
import com.example.sigmaindustry.presentation.Dimens.ServiceCardSize
import com.example.sigmaindustry.presentation.Dimens.SmallIconSize

@Composable
fun ServiceCard(
    modifier: Modifier = Modifier,
    services: SearchResult,
    onClick: ((SearchResult) -> Unit)? = null
) {

    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke(services) },

        ) {
        AsyncImage(
            modifier = Modifier
                .size(ServiceCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(services.pictures[0]).build(),
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
                text = services.name,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = colorResource(id = R.color.black),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = services.price.toString(),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.black)
                    )
                    Text(
                        text = services.category,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.black)
                    )
                }
                Spacer(modifier = Modifier.width(ExtraSmallPadding))
                Text(
                    text = services.description,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = R.color.black)
                )
            }
        }
    }
}

