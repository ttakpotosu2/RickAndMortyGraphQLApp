package com.example.rickandmortygraphql.presentation.characters

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
import com.example.rickandmortygraphql.presentation.navigation.Screen
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CharacterDetailScreen(
    characterViewModel: CharacterViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val character = characterViewModel.character.collectAsState()
    val scroll = rememberScrollState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF121010)),
        contentAlignment = Alignment.TopCenter
    ) {
        when(val data = character.value){
            is CharacterState.Success -> {
                val imagePainter = rememberAsyncImagePainter(
                    model = data.character?.image
                )
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(scroll),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ){
                    data.character?.name?.let {
                        Text(
                            text = it,
                            style = TextStyle(
                                fontSize = 40.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    data.character?.status?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(
                                    if (it == "Alive") Color.Green else Color.Red
                                )
                                .padding(horizontal = 16.dp),
                            style = TextStyle(
                                fontSize = 24.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    Text(
                        text = "Gender: ${data.character?.gender}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Text(
                        text = "Species: ${data.character?.species}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Text(
                        text = "Appears in:",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp
                    ) {
                        data.character?.episode?.forEach {episode ->
                            EpisodesItem(
                                episodeNumber = episode?.id,
                                onItemClick = {
                                    navHostController.navigate(
                                        Screen.EpisodeDetailScreen.route + "/${episode?.id}"
                                    )
                                }
                            )
                        }
                    }
                }
            }
            is CharacterState.Error -> {
                Text(text = "This is an Error Message")
            }
            is CharacterState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun EpisodesItem(
    episodeNumber: String?,
    onItemClick: () -> Unit
) {
    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = Color.White,
            shape = RoundedCornerShape(100.dp)
        )
        .padding(10.dp)
        .clickable { onItemClick() }
    ){
        Text(
            text = "Episode $episodeNumber",
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}