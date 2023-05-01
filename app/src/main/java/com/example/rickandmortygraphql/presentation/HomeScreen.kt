package com.example.rickandmortygraphql.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rickandmortygraphql.presentation.navigation.Screen

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121010)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "select a category to start",
            style = TextStyle(
                fontSize = 70.sp,
                color = Color.White
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                CategoryCard(
                    text = "x",
                    onClickAction = { navController.navigate(Screen.CharactersScreen.route)}
                )
            }
            item {
                CategoryCard(
                    text = "l",
                    onClickAction = { navController.navigate(Screen.LocationsScreen.route)}
                )
            }
            item {
                CategoryCard(
                    text = "e",
                    onClickAction = { navController.navigate(Screen.EpisodesScreen.route)}
                )
            }
        }
    }
}

@Composable
fun CategoryCard(
    text: String,
    onClickAction: () -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF3D3C3C))
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClickAction)
    ) {
        Text(
            text = text.uppercase(),
            style = TextStyle(
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}