package com.example.rickandmortygraphql.presentation.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortygraphql.domain.characters.CharacterList
import com.example.rickandmortygraphql.presentation.LoadingItem
import com.example.rickandmortygraphql.presentation.navigation.Screen

@Composable
fun CharactersScreen(
    navController: NavHostController,
    charactersViewModel: CharactersViewModel = hiltViewModel()
) {
    val characters = charactersViewModel.characters().collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121010))
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "select a character",
            style = TextStyle(
                fontSize = 70.sp,
                color = Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(
                count = characters.itemCount,
                key = characters.itemKey(),
                contentType = characters.itemContentType(
                )
            ) { index ->
                val item = characters[index]
                if (item != null) {
                    CharacterCard(
                        characters = item,
                        onItemClick = {
                            navController.navigate(
                                Screen.CharacterDetailScreen.route + "/${item.id}"
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterCard(
    characters: CharacterList,
    onItemClick: () -> Unit
) {
    val imagePainter = rememberAsyncImagePainter(
        model = characters.image
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .background(Color.White.copy(.1f))
            .clickable { onItemClick() }
            .clip(RoundedCornerShape(12.dp))
            .border(shape = RoundedCornerShape(12.dp), width = 1.dp, color = Color.White)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(6.dp)
        ) {
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(6.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = characters.name,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                ),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}