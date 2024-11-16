package com.proyectoplataformas.appguatemala.data.model

import com.proyectoplataformas.appguatemala.data.network.dto.noticia.NoticiaDto

data class Noticia(
    val type: CategoryType,
    val id: String,
    val name: String,
    val smallText: String,
    val actualText1: String,
    val actualText2: String,
    val image1: String,
    val image2: String,
    val image3: String
)

fun Noticia.mapToDto(): NoticiaDto = NoticiaDto(
    id = id,
    type = type.name,
    name = name,
    smallText = smallText,
    actualText1 = actualText1,
    actualText2 = actualText2,
    image1 = image1,
    image2 = image2,
    image3 = image3
)