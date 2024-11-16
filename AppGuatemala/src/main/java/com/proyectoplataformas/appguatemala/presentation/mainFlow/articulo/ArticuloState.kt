package com.proyectoplataformas.appguatemala.presentation.mainFlow.articulo

import com.proyectoplataformas.appguatemala.data.model.Noticia

data class ArticuloState(
    val data: Noticia? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false
)