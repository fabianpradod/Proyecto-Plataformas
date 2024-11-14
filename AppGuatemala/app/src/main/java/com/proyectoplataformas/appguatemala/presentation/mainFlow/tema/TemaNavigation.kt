package com.proyectoplataformas.appguatemala.presentation.mainFlow.tema


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia
import com.proyectoplataformas.appguatemala.tema.TemaRoute

import kotlinx.serialization.Serializable

@Serializable
data class TemaDestination(
    val type: CategoryType
)


fun NavController.navigateToTemaScreen(
    destination: TemaDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.temaScreen(
    onNoticiaBackClick: () -> Unit,
    onNoticiaClick: (Noticia) -> Unit,
    modifier: Modifier = Modifier
) {
    composable<TemaDestination> { backStackEntry ->
        val destination: TemaDestination = backStackEntry.toRoute()
        TemaRoute(
            onNoticiaBackClick = onNoticiaBackClick,
            onNoticiaClick = onNoticiaClick
        )
    }
}
