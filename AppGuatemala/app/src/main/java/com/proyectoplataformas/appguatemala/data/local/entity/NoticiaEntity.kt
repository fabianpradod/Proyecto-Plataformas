package com.proyectoplataformas.appguatemala.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia


@Entity
data class NoticiaEntity(
    @PrimaryKey val id: Int,
    val type: CategoryType,
    val name: String,
    val smallText: String,
    val actualText1: String,
    val actualText2: String,
    val image: String
)

fun Noticia.mapToEntity(): NoticiaEntity {
    return NoticiaEntity(
        id = id,
        type = type,
        name = name,
        smallText = smallText,
        actualText1 = actualText1,
        actualText2 = actualText2,
        image = image
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
        image = image
    )
}