package com.proyectoplataformas.appguatemala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import androidx.navigation.compose.NavHost
import com.proyectoplataformas.appguatemala.presentation.mainFlow.articulo.ArticuloDestination
import com.proyectoplataformas.appguatemala.presentation.mainFlow.articulo.articuloScreen
import com.proyectoplataformas.appguatemala.presentation.mainFlow.articulo.navigateToArticuloScreen
import com.proyectoplataformas.appguatemala.presentation.mainFlow.home.HomeDestination
import com.proyectoplataformas.appguatemala.presentation.mainFlow.home.homeScreen
import com.proyectoplataformas.appguatemala.presentation.mainFlow.home.navigateToHomeScreen
import com.proyectoplataformas.appguatemala.presentation.mainFlow.tema.TemaDestination
import com.proyectoplataformas.appguatemala.presentation.mainFlow.tema.navigateToTemaScreen
import com.proyectoplataformas.appguatemala.presentation.mainFlow.tema.temaScreen
import com.proyectoplataformas.appguatemala.presentation.theme.AppGuatemalaTheme
import com.proyectoplataformas.appguatemala.presentation.welcome.WelcomeDestination
import com.proyectoplataformas.appguatemala.presentation.welcome.welcomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGuatemalaTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = WelcomeDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        welcomeScreen (
                            onWelcomeClick = {
                                navController.navigateToHomeScreen(
                                    destination = HomeDestination
                                )
                            }
                        )
                        homeScreen(
                            onCategoryClick = { categoryType ->
                                navController.navigateToTemaScreen(
                                    destination = TemaDestination(
                                        type = categoryType
                                    ),
                                )
                            },
                            onNoticiaClick = { noticia ->
                                navController.navigateToArticuloScreen(
                                    destination = ArticuloDestination(
                                        id = noticia.id,
                                        category = noticia.type
                                    )
                                )
                            }
                        )
                        temaScreen(
                            onNoticiaBackClick = {
                                navController.navigateUp()
                            },
                            onNoticiaClick = { noticia ->
                                navController.navigateToArticuloScreen(
                                    destination = ArticuloDestination(
                                        id = noticia.id,
                                        category = noticia.type
                                    )
                                )
                            }
                        )
                        articuloScreen(
                            onArticuloBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}
