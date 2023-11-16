package com.example.sigmaindustry.presentation.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sigmaindustry.R
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.presentation.Dimens.ExtraSmallPadding
import com.example.sigmaindustry.presentation.Dimens.MediumPadding1
import com.example.sigmaindustry.presentation.Dimens.ServiceImageHeight
import com.example.sigmaindustry.presentation.details.components.DetailsTopBar
import com.example.sigmaindustry.util.UIComponent

@Composable
fun DetailsScreen(
    service: Service,
    event: (DetailsEvent) -> Unit,
    sideEffect: UIComponent?,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    println(service.name)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ) {
            item {
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.black
                    )
                )
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(service.pictures[0])
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ServiceImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding))
                Row (modifier =  Modifier.fillParentMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Price: ${service.price}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.black
                        )
                    )
                    // TODO correct category
                    Text(
                        text = "Category: ${service.category}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.black
                        )
                    )
                }
                // TODO add navigate to Provider
                Row (modifier =  Modifier.fillParentMaxWidth().clickable {  },
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Provider: ${service.provider}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.black
                        )
                    )
                    Text(
                        text = "Work time: ${service.provider}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.black
                        )
                    )
                }
                Spacer(modifier = Modifier.height(MediumPadding1))
                Text(
                    text = service.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.black
                    )
                )
                Spacer(modifier = Modifier.height(MediumPadding1))
                Row(modifier = Modifier.fillParentMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = {}) {
                        Text("Order")
                    }
                    Button(onClick = {}) {
                        Text("Rate")
                    }
                }
            }
        }
    }
}

