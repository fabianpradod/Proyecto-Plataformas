package com.proyectoplataformas.appguatemala.presentation.mainFlow.home

import com.proyectoplataformas.appguatemala.data.model.Noticia

data class HomeState(
    val lugaresNoticias: List<Noticia> = emptyList(),
    val artistasNoticias: List<Noticia> = emptyList(),
    val comidasNoticias: List<Noticia> = emptyList(),
    val instrumentosNoticias: List<Noticia> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
