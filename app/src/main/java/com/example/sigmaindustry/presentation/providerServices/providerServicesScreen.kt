package com.example.sigmaindustry.presentation.providerServices


import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sigmaindustry.data.remote.dto.Service
import com.example.sigmaindustry.presentation.Dimens
import com.example.sigmaindustry.presentation.home.components.ServiceCard

@Composable
fun ProviderServicesScreen(
    state: State<ProviderServicesState>,
    viewModel: ProviderServicesViewModel,
    event: (ProviderServicesEvent) -> Unit,
    navigateToAddService: () -> Unit,
    navigateToDetails: (Service) -> Unit,
    navigateToAddGeo: (Service) -> Unit
) {
    var needUpdate by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(needUpdate) {
        event(ProviderServicesEvent.GetServices)
        viewModel.errorHandler("Wait...")
    }
    Column(
        modifier = Modifier
            .padding(horizontal = Dimens.MediumPadding1)
    ) {

        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))


        LazyColumn() {
            state.value.services?.entries?.let { list ->
                items(list.size) {
                    var ser = state.value.services!!.entries[it]
                    ser = viewModel.changeServiceCategory(ser)
                    ListItemService(service = ser, navigateToAddGeo, navigateToDetails) {
                        event(
                            ProviderServicesEvent.DeleteService(
                                it.id
                            )
                        )
                        needUpdate = !needUpdate
                    }
                    Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
                    Divider()
                    Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
                }
            }
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
            onClick = {
                navigateToAddService()
            }) {
            Text(text = "Add new Service")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemService(
    service: Service,
    navigateToAddGeo: (Service) -> Unit,
    navigateToService: (Service) -> Unit,
    deleteService: (Service) -> Unit,
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    ListItem( modifier = Modifier.clickable { navigateToService(service) }, headlineText = { Text(service.name) },
        leadingContent = { AsyncImage(
            modifier = Modifier
                .size(Dimens.ServiceCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(service.pictures[0]).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        },
        overlineText = {
            Text(service.category)
        },
        supportingText = {
            Text("Price: ${if (service.price == 0.0) "Free" else service.price}")
        },
        trailingContent = { IconButton(onClick = { expanded = !expanded }) {
            Box(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center),
            ) {
                Icon(Icons.Filled.MoreVert, "DropDown")
                DropdownMenu(expanded = expanded, onDismissRequest = {  expanded = false }) {
                    DropdownMenuItem(text = { Text("Add location") }, onClick = { navigateToAddGeo(service);expanded = false },
                        leadingIcon = {Icon(Icons.Filled.Place, "Add location")})
                    DropdownMenuItem(text = { Text("Delete") }, onClick = { deleteService(service);expanded = false },
                        leadingIcon = {Icon(Icons.Filled.Delete, "delete")})
                }
            }

        }  })

}

