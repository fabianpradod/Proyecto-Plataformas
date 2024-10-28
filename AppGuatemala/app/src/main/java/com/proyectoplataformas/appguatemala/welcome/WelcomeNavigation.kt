package com.proyectoplataformas.appguatemala.welcome

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
data object WelcomeDestination

fun NavController.navigateToWelcomeScreen(
    destination: WelcomeDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.welcomeScreen(
    onWelcomeClick: () -> Unit
) {
    composable<WelcomeDestination> {
        WelcomeScreenRoute(
            onWelcomeClick = onWelcomeClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

