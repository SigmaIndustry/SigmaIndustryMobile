package com.example.sigmaindustry.presentation.createService

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sigmaindustry.data.remote.dto.AddService
import com.example.sigmaindustry.presentation.Dimens
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun CreateServiceScreen(
    event: (CreateServiceEvent) -> Unit,
    back: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var picture by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding1)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Your service will appear in search shortly")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name of service") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 70.dp)
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = picture,
            onValueChange = { picture = it },
            label = { Text("Enter URL of your service picture") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(
            text = error,
            color = Color.Red,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {

                loading = true
                GlobalScope.launch {
                    if (picture == "") {
                        event(
                            CreateServiceEvent.UpdateServiceRequest(
                                AddService(
                                    providerID = 1,
                                    name = name,
                                    pictures = listOf("https://i.ibb.co/71Q9Q5q/image.png"),
                                    category = "00",
                                    price = price.toFloat(),
                                    description = description
                                )
                            )
                        )
                    } else {
                        AddService(
                            providerID = 1,
                            name = name,
                            pictures = listOf(picture),
                            category = "00",
                            price = price.toFloat(),
                            description = description
                        )
                    }
                    event(CreateServiceEvent.CreateService)
                    delay(4000)
                    withContext(Dispatchers.Main) {
                        back()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Add service", fontSize = 18.sp)
        }

        if (!loading) return

        CircularProgressIndicator(
            modifier = Modifier.width(64.dp).padding(bottom = 70.dp, top = 70.dp),
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

