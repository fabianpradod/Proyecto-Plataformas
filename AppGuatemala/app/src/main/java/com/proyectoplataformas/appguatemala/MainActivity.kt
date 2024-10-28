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
import com.proyectoplataformas.appguatemala.ui.theme.AppGuatemalaTheme
import com.proyectoplataformas.appguatemala.welcome.WelcomeDestination
import androidx.navigation.compose.NavHost
import com.proyectoplataformas.appguatemala.home.HomeDestination
import com.proyectoplataformas.appguatemala.home.homeScreen
import com.proyectoplataformas.appguatemala.home.navigateToHomeScreen
import com.proyectoplataformas.appguatemala.noticia.NoticiasDestination
import com.proyectoplataformas.appguatemala.noticia.navigateToNoticiasScreen
import com.proyectoplataformas.appguatemala.noticia.noticiaScreen
import com.proyectoplataformas.appguatemala.tema.TemaDestination
import com.proyectoplataformas.appguatemala.tema.navigateToTemaScreen
import com.proyectoplataformas.appguatemala.tema.temaScreen
import com.proyectoplataformas.appguatemala.welcome.welcomeScreen


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
                                navController.navigateToNoticiasScreen(
                                    destination = NoticiasDestination(
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
                                navController.navigateToNoticiasScreen(
                                    destination = NoticiasDestination(
                                        id = noticia.id,
                                        category = noticia.type
                                    )
                                )
                            }
                        )
                        noticiaScreen(
                            onNoticiaBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}
