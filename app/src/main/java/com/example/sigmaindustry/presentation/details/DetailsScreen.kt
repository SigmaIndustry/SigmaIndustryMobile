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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sigmaindustry.R
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.presentation.Dimens.ExtraSmallPadding
import com.example.sigmaindustry.presentation.Dimens.MediumPadding1
import com.example.sigmaindustry.presentation.Dimens.ServiceImageHeight
import com.example.sigmaindustry.presentation.details.components.DetailsTopBar
import com.example.sigmaindustry.util.UIComponent
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.StepSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    service: Service,
    event: (DetailsEvent) -> Unit,
    viewModel: DetailsViewModel,
    sideEffect: UIComponent?,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    var textField by remember {
        mutableStateOf("")
    }
    var ratingBar by remember {
        mutableFloatStateOf(2.5f)
    }
    var orderField by remember {
         mutableStateOf("")
    }

    var showDialog by remember {
        mutableStateOf(false)
    }
    event(DetailsEvent.Load)
    val s = viewModel.changeServiceCategory(service)
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
                        text = "Price: ${if (service.price == 0.0) "Free" else service.price.toString() + " uah"}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.black
                        )
                    )
                    // TODO correct category
                    Text(
                        text = "Category: ${s.category}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.black
                        )
                    )
                }
                // TODO add navigate to Provider
                Row (modifier =  Modifier.fillParentMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Provider: ${service.provider}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(
                            id = R.color.black
                        ),
                        modifier = Modifier.clickable {  }
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
                Row {
                    OutlinedTextField(value = textField,
                        modifier = Modifier.width(250.dp), onValueChange =  { textField = it })
                    Button(onClick = { event(DetailsEvent.SendRate(
                        serviceId = service.id,
                        feedback = textField,
                        rating = ratingBar
                    )) }) {
                        Text("Rate")
                    }
                }
                RatingBar(value = ratingBar, onValueChange = {ratingBar = it},
                    onRatingChanged = {}, config = RatingBarConfig().stepSize(StepSize.HALF))
                when (viewModel.isTokenPresent) {
                    true -> Button(onClick = { event(DetailsEvent.ShowDialog) }) {
                        Text("Order")
                    }

                    else -> {}
                }

                var phoneNumber by remember {
                    mutableStateOf("")
                }

                when (viewModel.showDialog) {
                    true -> AlertDialog(
                        onDismissRequest = { event(DetailsEvent.HideDialog) },

                        confirmButton = { TextButton(onClick = {
                            event(DetailsEvent.SendOrder(service.id, "$orderField | My phone number is: $phoneNumber"))
                            event(DetailsEvent.HideDialog)
                        }) {
                            Text("Send")
                        } },
                        text = {
                            Column {
                                OutlinedTextField(value = phoneNumber, onValueChange = {phoneNumber = it},
                                    label = {Text("Phone")})
                                Spacer(modifier = Modifier.height(10.dp))
                                OutlinedTextField(value = orderField, onValueChange = {orderField = it},
                                    label = {Text("Order")})
                            }
                        }
                    )
                    else -> null
                }
            }
        }
    }
}


