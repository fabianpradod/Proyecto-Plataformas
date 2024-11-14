package com.proyectoplataformas.appguatemala.presentation.mainFlow.tema

import com.proyectoplataformas.appguatemala.data.model.Noticia

data class TemaState (
    val noticias: List<Noticia> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)