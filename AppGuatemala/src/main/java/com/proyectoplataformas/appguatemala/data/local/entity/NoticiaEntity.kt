package com.proyectoplataformas.appguatemala.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia


@Entity
data class NoticiaEntity(
    @PrimaryKey val id: String,
    val type: CategoryType,
    val name: String,
    val smallText: String,
    val actualText1: String,
    val actualText2: String,
    val image1: String,
    val image2: String,
    val image3: String
)

fun Noticia.mapToEntity(): NoticiaEntity {
    return NoticiaEntity(
        id = id,
        type = type,
        name = name,
        smallText = smallText,
        actualText1 = actualText1,
        actualText2 = actualText2,
        image1 = image1,
        image2 = image2,
        image3 = image3
        )
}

fun NoticiaEntity.mapToModel(): Noticia {
    return Noticia(
        id = id,
        type = type,
        name = name,
        smallText = smallText,
        actualText1 = actualText1,
        actualText2 = actualText2,
        image1 = image1,
        image2 = image2,
        image3 = image3
    )
}