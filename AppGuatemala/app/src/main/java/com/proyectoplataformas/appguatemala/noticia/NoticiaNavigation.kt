package com.proyectoplataformas.appguatemala.noticia


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.proyectoplataformas.appguatemala.CategoryType
import kotlinx.serialization.Serializable


@Serializable
data class NoticiasDestination(
    val id: Int,
    val category: CategoryType
)

fun NavController.navigateToNoticiasScreen(
    destination: NoticiasDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.noticiaScreen(
    onNoticiaBackClick: () -> Unit
) {
    composable<NoticiasDestination> { backStackEntry ->
        val destination: NoticiasDestination = backStackEntry.toRoute()
        NoticiaRoute(
            id = destination.id,
            type = destination.category,
            onNoticiaBackClick = onNoticiaBackClick
        )
    }
}

