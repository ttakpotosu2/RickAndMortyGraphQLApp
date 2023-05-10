package com.example.rickandmortygraphql.presentation.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.rickandmortygraphql.domain.locations.LocationsList
import com.example.rickandmortygraphql.presentation.navigation.Screen

@Composable
fun LocationsScreen(
    navHostController: NavHostController,
    locationsViewModel: LocationsViewModel = hiltViewModel()
) {
    val location = locationsViewModel.locations().collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121010))
    ) {
      //  Spacer(modifier = Modifier.height(16.dp))
        IconButton(
            onClick = { navHostController.navigateUp() },
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
        Text(
            text = "where do you want to go?",
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
                count = location.itemCount,
                key = location.itemKey(),
                contentType = location.itemContentType(
                )
            ) { index ->
                val item = location[index]
                if (item != null) {
                    LocationCard(
                        location = item,
                        onItemClick = {
                            navHostController.navigate(
                                Screen.LocationDetailScreen.route + "/${item.id}"
                            )
                        }
                    )
                }
            }
            location.apply {
                when{
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(vertical = 200.dp),
                                    color = Color.White
                                )
                            }

                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            color = Color.White
                        ) }
                    }
                }
            }
        }
    }
}

@Composable
fun LocationCard(
    location: LocationsList,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White.copy(.1f))
            .clickable { onItemClick() }
            .clip(RoundedCornerShape(12.dp))
            .border(shape = RoundedCornerShape(12.dp), width = 1.dp, color = Color.White)
            .padding(8.dp)
    ) {
        Text(
            text = location.name,
            style = TextStyle(
                color = Color.White,
                fontSize = 38.sp,
            ),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "${location.dimension} || ${location.name}",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}