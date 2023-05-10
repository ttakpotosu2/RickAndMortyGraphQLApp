package com.example.rickandmortygraphql.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            .background(Color(0xFF121010))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "select a category to start",
            style = TextStyle(
                fontSize = 70.sp,
                color = Color.White
            )
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CategoryCard(
                text = "x",
                onClickAction = { navController.navigate(Screen.CharactersScreen.route)}
            )
            CategoryCard(
                text = "l",
                onClickAction = { navController.navigate(Screen.LocationsScreen.route)}
            )
            CategoryCard(
                text = "e",
                onClickAction = { navController.navigate(Screen.EpisodesScreen.route)}
            )
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
            .fillMaxWidth()
            .height(140.dp)
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