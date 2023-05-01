package com.example.rickandmortygraphql.presentation.locations

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortygraphql.LocationQuery
import com.example.rickandmortygraphql.presentation.navigation.Screen
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun LocationDetailScreen(
    navHostController: NavHostController,
    viewModel: LocationViewModel = hiltViewModel(),
) {
    val location = viewModel.location.collectAsState()
    val scroll = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF121010))
    ) {
        when (location.value) {
            is LocationState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(scroll),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    (location.value as LocationState.Success).location?.name?.let {
                        Text(
                            text = it,
                            style = TextStyle(
                                fontSize = 40.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Text(
                        text = "Type: ${(location.value as LocationState.Success).location?.type}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Dimension: ${(location.value as LocationState.Success).location?.dimension}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Residents",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                    FlowRow(
                        mainAxisSpacing = 6.dp,
                        crossAxisSpacing = 6.dp
                    ) {
                        (location.value as LocationState.Success)
                            .location
                            ?.residents
                            ?.forEach { resident ->
                                if (resident != null) {
                                    LocationResidentsItem(resident = resident) {
                                        navHostController.navigate(
                                            Screen.CharacterDetailScreen.route + "/${resident.id}"
                                        )
                                    }
                                }
                            }
                    }
                }
            }
            is LocationState.Error -> {
                Text(text = "This is an Error Message")
            }
            is LocationState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(vertical = 200.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun LocationResidentsItem(
    resident: LocationQuery.Resident,
    onItemClick: () -> Unit
) {

    val imagePainter = rememberAsyncImagePainter(
        model = resident.image
    )

    Column(modifier = Modifier
        .border(
            width = 1.dp,
            color = Color.White,
            shape = RoundedCornerShape(12.dp)
        )
        .padding(10.dp)
        .clickable { onItemClick() }
    ){
        Image(painter = imagePainter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(6.dp))
        )
        resident.name?.let {
            Text(
                text = it,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}