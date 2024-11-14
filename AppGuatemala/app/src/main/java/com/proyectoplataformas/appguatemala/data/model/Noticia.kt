package com.proyectoplataformas.appguatemala.data.model

data class Noticia(
    val type: CategoryType,
    val id: Int,
    val name: String,
    val smallText: String,
    val actualText1: String,
    val actualText2: String,
    val image: String
)