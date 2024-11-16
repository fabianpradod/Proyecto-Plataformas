package com.proyectoplataformas.appguatemala.data.network.dto.noticia

import com.proyectoplataformas.appguatemala.data.local.entity.NoticiaEntity
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia
import kotlinx.serialization.Serializable

@Serializable
data class NoticiaDto(
    val type: String = "",
    val id: String = "",
    val name: String = "",
    val smallText: String = "",
    val actualText1: String = "",
    val actualText2: String = "",
    val image1: String = "",
    val image2: String = "",
    val image3: String =""
)

fun NoticiaDto.toEntity(): NoticiaEntity {
    return NoticiaEntity(
        id = id,
        type = CategoryType.valueOf(type),
        name = name,
        smallText = smallText,
        actualText1 = actualText1,
        actualText2 = actualText2,
        image1 = image1,
        image2 = image2,
        image3 = image3
    )
}

fun NoticiaDto.mapToModel(id: String): Noticia = Noticia(
    id = id,
    type = CategoryType.valueOf(type),
    name = name,
    smallText = smallText,
    actualText1 = actualText1,
    actualText2 = actualText2,
    image1 = image1,
    image2 = image2,
    image3 = image3
)