package com.example.rickandmortygraphql.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortygraphql.presentation.HomeScreen
import com.example.rickandmortygraphql.presentation.characters.CharacterDetailScreen
import com.example.rickandmortygraphql.presentation.characters.CharactersScreen
import com.example.rickandmortygraphql.presentation.episodes.EpisodeDetailScreen
import com.example.rickandmortygraphql.presentation.episodes.EpisodesScreen
import com.example.rickandmortygraphql.presentation.locations.LocationDetailScreen
import com.example.rickandmortygraphql.presentation.locations.LocationsScreen

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navHostController)
        }
        composable(route = Screen.CharactersScreen.route) {
            CharactersScreen(navController = navHostController)
        }
        composable(route = Screen.CharacterDetailScreen.route + "/{characterId}") {
            CharacterDetailScreen(navHostController = navHostController)
        }
        composable(route = Screen.EpisodesScreen.route){
            EpisodesScreen(navHostController = navHostController)
        }
        composable(route = Screen.EpisodeDetailScreen.route + "/{episodeId}"){
            EpisodeDetailScreen(navController = navHostController)
        }
        composable(route = Screen.LocationsScreen.route){
            LocationsScreen(navHostController = navHostController)
        }
        composable(route = Screen.LocationDetailScreen.route + "/{locationId}"){
            LocationDetailScreen(navHostController = navHostController)
        }
    }
}