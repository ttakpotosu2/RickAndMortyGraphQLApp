package com.example.rickandmortygraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortygraphql.presentation.navigation.NavigationGraph
import com.example.rickandmortygraphql.presentation.theme.RickAndMortyGraphQLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyGraphQLTheme {
                val navController = rememberNavController()
                NavigationGraph(navHostController = navController)
            }
        }
    }
}
