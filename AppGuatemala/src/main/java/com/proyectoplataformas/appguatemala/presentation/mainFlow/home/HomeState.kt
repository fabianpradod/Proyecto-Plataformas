package com.proyectoplataformas.appguatemala.presentation.mainFlow.home

import com.proyectoplataformas.appguatemala.data.model.Noticia

data class HomeState(
    val allNoticias: List<Noticia> = emptyList(),
    val lugaresNoticias: List<Noticia> = emptyList(),
    val personasNoticias: List<Noticia> = emptyList(),
    val comidasNoticias: List<Noticia> = emptyList(),
    val arteNoticias: List<Noticia> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
