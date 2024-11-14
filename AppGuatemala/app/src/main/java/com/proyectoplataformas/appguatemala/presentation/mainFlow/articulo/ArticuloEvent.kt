package com.proyectoplataformas.appguatemala.presentation.mainFlow.articulo

interface ArticuloEvent {
    data object ForceError: ArticuloEvent
    data object RetryClick: ArticuloEvent
}