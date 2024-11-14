package com.proyectoplataformas.appguatemala.presentation.mainFlow.home

sealed interface HomeEvent {
    data object ForceError: HomeEvent
    data object RetryClick: HomeEvent
}