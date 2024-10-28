package com.proyectoplataformas.appguatemala.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.proyectoplataformas.appguatemala.CategoryType
import com.proyectoplataformas.appguatemala.Noticia
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
            onNoticiaClick = onNoticiaClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

