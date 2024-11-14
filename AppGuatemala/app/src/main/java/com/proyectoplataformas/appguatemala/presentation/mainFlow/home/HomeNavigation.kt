package com.proyectoplataformas.appguatemala.presentation.mainFlow.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia
import com.proyectoplataformas.appguatemala.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeDestination

fun NavController.navigateToHomeScreen(
    destination: HomeDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onCategoryClick: (CategoryType) -> Unit,
    onNoticiaClick: (Noticia) -> Unit,
) {
    composable<HomeDestination> {
        HomeRoute(
            onCategoryClick = onCategoryClick,
            onNoticiaClick = onNoticiaClick
        )
    }
}

