package com.proyectoplataformas.appguatemala.presentation.mainFlow.articulo


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.noticia.ArticuloRoute
import kotlinx.serialization.Serializable


@Serializable
data class ArticuloDestination(
    val id: Int,
    val category: CategoryType
)

fun NavController.navigateToArticuloScreen(
    destination: ArticuloDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.articuloScreen(
    onArticuloBackClick: () -> Unit
) {
    composable<ArticuloDestination> { backStackEntry ->
        val destination: ArticuloDestination = backStackEntry.toRoute()
        ArticuloRoute(
            onArticuloBackClick = onArticuloBackClick
        )
    }
}

