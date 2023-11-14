package com.example.sigmaindustry.presentation.details

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sigmaindustry.R
import com.example.sigmaindustry.data.remote.dto.SearchResult
import com.example.sigmaindustry.presentation.Dimens.MediumPadding1
import com.example.sigmaindustry.presentation.Dimens.ServiceImageHeight
import com.example.sigmaindustry.presentation.details.components.DetailsTopBar
import com.example.sigmaindustry.util.UIComponent

@Composable
fun DetailsScreen(
    service: SearchResult,
    event: (DetailsEvent) -> Unit,
    sideEffect: UIComponent?,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when(sideEffect){
                is UIComponent.Toast ->{
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(DetailsEvent.RemoveSideEffect)
                }
                else -> Unit
            }
        }
    }

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
                Spacer(modifier = Modifier.height(MediumPadding1))
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.black
                    )
                )
                Text(
                    text = service.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.black
                    )
                )
            }
        }
    }
}

