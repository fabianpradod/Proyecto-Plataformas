package com.proyectoplataformas.appguatemala.presentation.mainFlow.tema

interface TemaEvent {
    data object ForceError: TemaEvent
    data object RetryClick: TemaEvent
}